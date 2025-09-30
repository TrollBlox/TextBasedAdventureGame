package game.adventure.commands;

import game.adventure.main.AdventureManager;
import game.adventure.util.Constants;

public class Inventory implements Command<Void> {
    @Override
    public void execute(Void arg) {
        System.out.println("Health: " + AdventureManager.getPlayer().getHealth());
        int damage = AdventureManager.getPlayer().getDamage();
        System.out.println("Damage: " + damage);
        System.out.println();
        printItems();
        if (AdventureManager.getPlayer().hasEquip()) {
            System.out.println("\nEquipped item: \n" + AdventureManager.getPlayer().getEquip().getName() + " (" + AdventureManager.getPlayer().getDamage() + " damage): " + AdventureManager.getPlayer().getEquip().getDescription());
        }
    }

    private void printItems() {
        if (AdventureManager.getPlayer().getInventory().isEmpty() || (AdventureManager.getPlayer().getInventory().size() == 1 && !AdventureManager.getPlayer().getEquip().equals(Constants.GENERIC_ITEM))) {
            System.out.println("You have nothing in your inventory!");
            return;
        }

        System.out.println("Your inventory: ");
        for (int i = 0; i < AdventureManager.getPlayer().getInventory().size(); i++) {
            String nameSuffix = AdventureManager.getPlayer().getItem(i).getDamage() == -1 ? ": " : " (" + AdventureManager.getPlayer().getItem(i).getDamage() + " damage): ";
            System.out.println(AdventureManager.getPlayer().getItem(i).getName() + nameSuffix + AdventureManager.getPlayer().getItem(i).getDescription());
        }
    }

    @Override
    public String getName() {
        return "inventory";
    }

    @Override
    public String getHelpMessage() {
        return "Shows your inventory";
    }
}
