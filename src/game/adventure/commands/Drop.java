package game.adventure.commands;

import game.adventure.gameobjects.Item;
import game.adventure.main.AdventureManager;

public class Drop implements Command<Item> {
    @Override
    public void execute(Item arg) {
        if (!AdventureManager.getPlayer().hasItem(arg)) {
            System.out.println("You don't have that item!");
            return;
        }
        AdventureManager.getPlayer().removeItem(arg);
        AdventureManager.getCurrentRoom().addItem(arg);
        System.out.println("You dropped the " + arg.getName() + ".");
    }

    @Override
    public String getName() {
        return "drop";
    }

    @Override
    public String getHelpMessage() {
        return "drop <item>";
    }
}
