package game.adventure.gameobjects;

import game.adventure.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Item equip;
    private int health;
    private final List<Item> inventory;

    public Player(Builder builder) {
        this.health = builder.health;
        this.inventory = builder.inventory;
        this.equip = builder.equip;
    }

    public boolean isEquip(Item item) {
        return equip.equals(item);
    }

    public void setEquip(String item) {
        setEquip(getItem(item));
    }

    public void removeEquip() {
        setEquip(Constants.GENERIC_ITEM);
    }

    public void addItem(Item newItem) {
        System.out.println("New Item: " + newItem.getName());
        inventory.add(newItem);
    }

    public int getDamage() {
        return getEquip().getDamage();
    }

    public Item getItem(int i) {
        return inventory.get(i);
    }

    public Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }
        return Constants.GENERIC_ITEM;
    }

    public boolean hasItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) return true;
        }
        return false;
    }

    public void removeItem(Item oldItem) {
        if (isEquip(oldItem)) removeEquip();
        else inventory.remove(oldItem);
    }

    public void removeItem(String oldItem) {
        removeItem(getItem(oldItem));
    }

    public void clearInventory() {
        inventory.clear();
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Item getEquip() {
        return equip;
    }

    public void setEquip(Item equip) {
        if (!this.equip.equals(Constants.GENERIC_ITEM)) inventory.add(this.equip);
        inventory.remove(equip);
        this.equip = equip;
    }

    public boolean hasEquip() {
        return !getEquip().equals(Constants.GENERIC_ITEM);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public static class Builder {
        private Item equip = Constants.GENERIC_ITEM;
        private int health = 1;
        private List<Item> inventory = new ArrayList<>();

        public Builder equip(Item equip) {
            this.equip = equip;
            return this;
        }

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder inventory(List<Item> inventory) {
            this.inventory = inventory;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}
