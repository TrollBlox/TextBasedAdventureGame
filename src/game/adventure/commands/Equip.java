package game.adventure.commands;

import game.adventure.gameobjects.Item;
import game.adventure.main.AdventureManager;

public class Equip implements Command<Item> {
    @Override
    public void execute(Item arg) {
        if (!AdventureManager.getPlayer().hasItem(arg)) {
            System.out.println("You don't have the item " + arg.getName() + "!");
            return;
        }

        if (arg.isEquippable()) {
            System.out.println("You can't equip " + arg.getName() + "!");
            return;
        }
        AdventureManager.getPlayer().setEquip(arg);
        System.out.println("Equipped " + arg.getName());
    }

    @Override
    public String getName() {
        return "equip";
    }

    @Override
    public String getHelpMessage() {
        return "equip <item>";
    }
}
