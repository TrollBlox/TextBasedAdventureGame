package game.adventure.main;

import game.adventure.interfaces.Adventure;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<Adventure> adventures = fileManager.getAdventures();
        boolean adventureChosen = false;
        int input = -2;
        do {
            System.out.println("Adventures available: ");
            System.out.println("0: " + AdventureManager.getCurrentAdventure().getName());
            for (int i = 0; i < adventures.size(); i++) {
                System.out.println(i + 1 + ": " + adventures.get(i).getName());
            }
            System.out.println("Which adventure do you want to play? Enter 0 - " + adventures.size());
            System.out.print("> ");
            try {
                input = Integer.parseInt(s.nextLine()) - 1;
            } catch (NumberFormatException e) {
                continue;
            }
            if (input == -1) GameLogic.start(); // TODO: REMOVE LATER
            if (input > 0 && input < adventures.size()) {
                adventureChosen = true;
            } else {
                System.out.println("Please choose a valid number!");
            }
        } while (!adventureChosen);
        AdventureManager.setCurrentAdventure(adventures.get(input));
        GameLogic.start();
    }
}
