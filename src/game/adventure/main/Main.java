package game.adventure.main;

import game.adventure.interfaces.Adventure;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        List<Adventure> adventures = fileManager.getAdventures();
        if (adventures.isEmpty()) {
            System.out.println("no advntures found");
            return;
        }
        System.out.println("Adventures available: ");
        for (int i = 0; i < adventures.size(); i++) {
            System.out.println(i + 1 + ": " + adventures.get(i).getClass().getSimpleName());
        }
        System.out.println("Which adventure do you want to play? Enter 1 - " + adventures.size());
        System.out.print("> ");
        int input = Integer.parseInt(s.nextLine()) - 1;
        if (input < 0 || input > adventures.size()) {
            System.out.println("wtf man");
            return;
        }
        AdventureManager.setCurrentAdventure(adventures.get(input));
        AdventureManager.getCurrentAdventure().init();
        GameLogic.start();
    }
}
