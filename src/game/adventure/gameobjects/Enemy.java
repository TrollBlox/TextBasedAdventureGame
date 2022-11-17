package game.adventure.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class Enemy {

    private String name, description;

    private int health, damage;

    private List<Item> drops;

    public Enemy(String name, String description, int health, int damage, List<Item> drops) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.drops = drops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public List<Item> getDrops() {
        return drops;
    }

    public void setDrops(List<Item> drops) {
        this.drops = drops;
    }

    public void addDrop(Item drop) {
        drops.add(drop);
    }

    public void removeDrop(Item drop) {
        drops.remove(drop);
    }

}
