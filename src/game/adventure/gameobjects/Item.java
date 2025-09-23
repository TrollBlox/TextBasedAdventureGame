package game.adventure.gameobjects;

public class Item {
    private String name;
    private String description;
    private boolean canPickUp;
    private int damage;

    /** a damage value of -1 would make the item non-equippable */
    public Item(String name, String description, boolean canPickUp, int damage) {
        this.name = name;
        this.description = description;
        this.canPickUp = canPickUp;
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isName(String name) {
        return getName().equalsIgnoreCase(name);
    }

    public boolean canPickUp() {
        return canPickUp;
    }

    public void setCanPickUp(boolean canPickUp) {
        this.canPickUp = canPickUp;
    }

    public boolean getEquippable() {
        return damage != -1;
    }

    /** a damage value of -1 would make the item non-equippable */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
