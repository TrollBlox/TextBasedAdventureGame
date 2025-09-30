package game.adventure.commands;

import game.adventure.gameobjects.Item;
import game.adventure.main.AdventureManager;

public class Take implements Command<Item> {
    @Override
    public void execute(Item arg) {
        if (!AdventureManager.getCurrentRoom().hasItem(arg)) {
            System.out.println("Can't find that item!");
            return;
        }
        if (!arg.canPickUp()) {
            System.out.println("You can't take that item!");
            return;
        }
        AdventureManager.getCurrentRoom().removeItem(arg);
        AdventureManager.getPlayer().addItem(arg);
        System.out.println("You took the " + arg.getName() + ".");
    }

    @Override
    public String getName() {
        return "take";
    }

    @Override
    public String getHelpMessage() {
        return "take <item>";
    }
}
