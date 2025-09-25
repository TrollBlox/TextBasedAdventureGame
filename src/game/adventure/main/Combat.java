package game.adventure.main;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.util.Adventure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Combat {
    private boolean inCombat = true;
    private static boolean timeExpired;
    private final Scanner s = new Scanner(System.in);
    private final Random r = new Random();
    private final Adventure currentAdventure;

    private enum Operator {
        ADD(0, '+', Double::sum),
        SUBTRACT(1, '-', (a, b) -> a - b),
        MULTIPLY(2, '*', (a, b) -> a * b),
        DIVIDE(3, '/', (a, b) -> b == 0 ? Double.NaN : a / b);

        private final int index;
        private final char symbol;
        private final BiFunction<Double, Double, Double> operator;

        Operator(int index, char symbol, BiFunction<Double, Double, Double> operator) {
            this.index = index;
            this.symbol = symbol;
            this.operator = operator;
        }

        public BiFunction<Double, Double, Double> getOperator() {
            return operator;
        }

        public int getIndex() {
            return index;
        }

        public char getSymbol() {
            return symbol;
        }

        public static Operator getOperatorFromIndex(int index) {
            for (Operator operator : Operator.values()) {
                if (operator.getIndex() == index) return operator;
            }
            throw new IndexOutOfBoundsException();
        }

    }

    public Combat() {
        currentAdventure = AdventureManager.getCurrentAdventure();
        System.out.println("You were attacked when you entered the " + AdventureManager.getCurrentRoom().getName() + "!");
        for (int i = 0; i < AdventureManager.getCurrentRoom().getEnemies().size(); i++) {
            Enemy e = AdventureManager.getCurrentRoom().getEnemies().get(i);
            System.out.print("Enemies near you: ");
            String separator = i == AdventureManager.getCurrentRoom().getEnemies().size() - 1 ? "." : ", ";
            System.out.println(e.getName() + " (" + e.getHealth() + " hp " + e.getDamage() + " damage)" + separator);
        }
        combatLoop();
    }

    private void combatLoop() {
        System.out.println("\n\nTo attack in combat, answer the question correctly within the time limit.");
        System.out.println("Press enter when you are ready to begin");
        s.nextLine();
        while (inCombat) {
            timeExpired = false;
            int a = r.nextInt(10);
            int b = r.nextInt(10);
//            Operator operator = Operator.getOperatorFromIndex(r.nextInt(Operator.values().length));
            Operator operator = Operator.ADD;
            int seconds = r.nextInt(3, 9);
            System.out.println("You have " + seconds + " seconds to solve this problem.");
            System.out.print(a + " " + operator.getSymbol() + " " + b + " = ");

            String input = scan(seconds);
            if (timeExpired) System.out.println();

            double answer = operator.getOperator().apply((double) a, (double) b);
            double guess;
            boolean correct;
            try {
                guess = Double.parseDouble(input);
                correct = guess == answer;
            } catch (NumberFormatException e) {
                correct = false;
            }

            if (!timeExpired && correct) attack();
            if (!timeExpired && !correct) System.out.println("Wrong answer!");
            if (timeExpired) System.out.println("Out of time!");

            if (AdventureManager.getCurrentRoom().getEnemies().isEmpty()) {
                System.out.println("You have defeated all the enemies!");
                inCombat = false;
            }

            for (Enemy e : AdventureManager.getCurrentRoom().getEnemies()) {
                currentAdventure.getPlayer().setHealth(currentAdventure.getPlayer().getHealth() - e.getDamage());
                if (currentAdventure.getPlayer().getHealth() <= 0) {
                    GameLogic.playerDeath(e.getName());
                    return;
                }
                System.out.println("The " + e.getName() + " did " + e.getDamage() + " damage to you!");
                System.out.println("You have " + AdventureManager.getPlayer().getHealth() + " health.");
            }

            if (inCombat) {
                System.out.print("Press enter to continue...");
                s.nextLine();
            }
        }
    }

    private void attack() {
        List<Enemy> toRemove = new ArrayList<>();

        for (Enemy e : AdventureManager.getCurrentRoom().getEnemies()) {
            int damage = AdventureManager.getPlayer().getDamage();

            e.setHealth(e.getHealth() - damage);
            System.out.println("You did " + damage + " damage to the " + e.getName() + " (hp: " + e.getHealth() + ")");

            if (e.getHealth() <= 0) {
                System.out.println("You killed the " + e.getName());
                toRemove.add(e);
                System.out.print("It dropped: ");
                for (int i = 0; i < e.getDrops().size(); i++) {
                    Item drop = e.getDrops().get(i);
                    AdventureManager.getCurrentRoom().addItem(drop);
                    String separator = i == e.getDrops().size() - 1 ? "." : ", ";

                    System.out.println(e.getDrops().get(i).getName() + separator);
                }
            }
        }

        try {
            AdventureManager.getCurrentRoom().getEnemies().removeAll(toRemove);
        } catch (NullPointerException ignore) {
        }
    }

    private String scan(int seconds) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < seconds * 1000L) {
            try {
                if (reader.ready()) {
                    return reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error reading input.");
            }
        }
        timeExpired = true;
        return "";
    }
}
