package game.adventure.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends GameObject {
    private int health, damage;

    private List<Item> drops;

    public Enemy(Builder builder) {
        super(builder);
        this.health = builder.health;
        this.damage = builder.damage;
        this.drops = builder.drops;
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

    public static class Builder extends GameObject.Builder<Builder>{
        private int health = 1;
        private int damage = 0;
        private final List<Item> drops = new ArrayList<>();

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder drops(List<Item> drops) {
            this.drops.addAll(drops);
            return this;
        }

        public Builder drops(Item... items) {
            this.drops.addAll(List.of(items));
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Enemy build() {
            return new Enemy(this);
        }

    }

}
