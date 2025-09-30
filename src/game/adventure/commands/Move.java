package game.adventure.commands;

import game.adventure.main.AdventureManager;
import game.adventure.main.Combat;
import game.adventure.util.Direction;
import game.adventure.util.HelperFunctions;

public class Move implements Command<Direction> {
    @Override
    public void execute(Direction arg) {
        if (AdventureManager.getCurrentRoom().getDirection(arg).isEmpty()) {
            System.out.println("You can't go " + arg.getString() + " here!");
            return;
        }
        AdventureManager.setCurrentRoom(AdventureManager.getContext().getRoom(AdventureManager.getCurrentRoom().getDirection(arg)));
        String aOrAn = HelperFunctions.startsWithVowel(AdventureManager.getCurrentRoom().getName()) ? "n " : " ";
        System.out.println("You find yourself in a" + aOrAn + AdventureManager.getCurrentRoom().getName());
        if (!AdventureManager.getCurrentRoom().getEnemies().isEmpty()) {
            new Combat();
        }
    }

    @Override
    public String getName() {
        return "move";
    }

    @Override
    public String getHelpMessage() {
        return "move <north, south, east, or west>";
    }
}
