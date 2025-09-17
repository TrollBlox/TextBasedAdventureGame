package game.adventure.gameobjects;

import java.util.List;

public class Player {
    private int equip;

    private int health;

    private final List<Item> playerInventory;

    /** equip should be an index in the players inventory. if the number is out of bounds, the player will automatically have no equip. -1 == no equip */
    public Player(int health, List<Item> playerInventory, int equip) {
        this.health = health;
        this.playerInventory = playerInventory;
        if (equip + 1 > playerInventory.size() || equip + 1 < 0 || playerInventory.isEmpty()) {
            this.equip = -1;
        } else {
            this.equip = equip;
        }
    }

    public boolean isEquip(String item) {
        int index = getInt(item);
        if (index == -1) {
            return false;
        }
        return index == getEquip();
    }

    public void setEquip(String item) throws IndexOutOfBoundsException {
        int index = getInt(item);
        setEquip(index);
    }

    public void removeEquip() {
        setEquip(-1);
    }

    public void addItem(Item newItem) {
        playerInventory.add(newItem);
    }

    private int getInt(String item) {
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getName().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }

    public int getDamage() {
        if (getEquip() == -1) return -1;
        return getItem(getEquip()).getDamage();
    }

    public Item getEquipItem() {
        return getItem(getEquip());
    }

    public Item getItem(int i) {
        return playerInventory.get(i);
    }

    public Item getItem(String item) throws IndexOutOfBoundsException {
        int i = getInt(item);
        if (i == -1) {
            throw new IndexOutOfBoundsException();
        }
        return playerInventory.get(i);
    }

    public boolean hasItem(String item) {
        return getInt(item) != -1;
    }

    public void removeItem(Item oldItem) {
        playerInventory.remove(oldItem);
    }

    public void removeItem(String oldItem) throws IndexOutOfBoundsException {
        int index = getInt(oldItem);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        removeItem(getItem(index));
    }

    public void clearInventory() {
        playerInventory.clear();
    }

    public List<Item> getInventory() {
        return playerInventory;
    }

    public int getEquip() {
        return equip;
    }

    public void setEquip(int equip) {
        this.equip = equip;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
