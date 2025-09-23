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
        int input;
        do {
            System.out.println("Adventures available: ");
            for (int i = 0; i < adventures.size(); i++) {
                System.out.println(i + 1 + ": " + adventures.get(i).getName());
            }
            System.out.println("Which adventure do you want to play? Enter 1 - " + adventures.size());
            System.out.print("> ");
            input = Integer.parseInt(s.nextLine()) - 1;
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
