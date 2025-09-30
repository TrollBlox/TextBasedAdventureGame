package game.adventure.commands;

import game.adventure.gameobjects.Enemy;
import game.adventure.main.AdventureManager;
import game.adventure.util.Direction;

public class Look implements Command<Void> {
    @Override
    public void execute(Void arg) {
        System.out.println(AdventureManager.getCurrentRoom().getName() + ": " + AdventureManager.getCurrentRoom().getDescription());
        printItems();
        printDirections();
        printEnemies();
    }

    private void printItems() {
        if (AdventureManager.getCurrentRoom().getItems().isEmpty()) {
            System.out.println("There are no items in this room!");
            return;
        }

        System.out.print("Items near you: ");
        for (int i = 0; i < AdventureManager.getCurrentRoom().getItems().size(); i++) {
            String separator = i == AdventureManager.getCurrentRoom().getItems().size() - 1 ? "." : ", ";

            System.out.print(AdventureManager.getCurrentRoom().getItem(i).getName() + separator);
        }
        System.out.println();
    }

    private void printDirections() {
        for (Direction direction : Direction.values()) {
            if (AdventureManager.getCurrentRoom().getDirection(direction).isEmpty()) return;
            if (AdventureManager.getContext().getRoom(AdventureManager.getCurrentRoom().getDirection(direction)).getHasVisited()) {
                System.out.println("To the " + direction.getString() +": " + AdventureManager.getContext().getRoom(AdventureManager.getCurrentRoom().getDirection(direction)).getName());
                return;
            }
            System.out.println("There is something to the " + direction.getString() + ".");
        }
    }

    private void printEnemies() {
        if (AdventureManager.getCurrentRoom().getEnemies().isEmpty()) return;
        for (int i = 0; i < AdventureManager.getCurrentRoom().getEnemies().size(); i++) {
            Enemy e = AdventureManager.getCurrentRoom().getEnemies().get(i);
            System.out.println("Enemies near you: ");
            System.out.println(e.getName() + ": " + e.getDescription());
        }
    }

    @Override
    public String getName() {
        return "look";
    }

    @Override
    public String getHelpMessage() {
        return "Shows information about your current room";
    }
}
