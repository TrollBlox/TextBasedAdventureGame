package game.adventure.commands;

import game.adventure.gameobjects.Item;

public class LookAtItem implements Command<Item> {
    @Override
    public void execute(Item arg) {
        System.out.println(arg.getName() + ": " + arg.getDescription());
    }

    @Override
    public String getName() {
        return "look";
    }

    @Override
    public String getHelpMessage() {
        return "look <item>";
    }
}
