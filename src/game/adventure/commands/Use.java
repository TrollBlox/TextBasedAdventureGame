package game.adventure.commands;

import game.adventure.gameobjects.Item;
import game.adventure.main.AdventureManager;
import game.adventure.objects.ItemPair;

public class Use implements Command<ItemPair> {
    @Override
    public void execute(ItemPair arg) {
        Item item1 = arg.item1();
        Item item2 = arg.item2();
        if (!((AdventureManager.getPlayer().hasItem(item1) || (AdventureManager.getCurrentRoom().hasItem(item1) && !item1.canPickUp())) &&
                ((AdventureManager.getPlayer().hasItem(item2) || (AdventureManager.getCurrentRoom().hasItem(item2) && !item2.canPickUp()))))) {
            System.out.println("You don't have that item!");
            return;
        }
        AdventureManager.getCurrentAdventure().useFetcher().get(arg).run();
    }

    @Override
    public String getName() {
        return "use";
    }

    @Override
    public String getHelpMessage() {
        return "use <item> on <item2>";
    }
}
