package game.adventure.gameobjects;

import game.adventure.util.Constants;
import game.adventure.util.Direction;
import java.util.*;

public class Room extends GameObject {
    private final Map<Direction, String> directions;
    private final List<Item> items;
    private final List<Enemy> enemies;
    private boolean hasVisited;

    public Room(Builder builder) {
        super(builder);
        this.items = builder.items;
        this.enemies = builder.enemies;
        directions = new HashMap<>();
        for (Direction direction : Direction.values()) {
            directions.put(direction, "");
        }
    }

    public String getDirection(Direction direction) {
        return directions.get(direction);
    }

    public void setDirection(Direction direction, String north) {
        directions.put(direction, north);
    }

    public Map<Direction, String> getDirections() {
        return directions;
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(Item... items) {
        this.items.addAll(List.of(items));
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void clearItems() {
        items.clear();
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) return item;
        }
        return Constants.GENERIC_ITEM;
    }

    public boolean hasItem(String item) {
        return !getItem(item).equals(Constants.GENERIC_ITEM);
    }

    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    public boolean getHasVisited() {
        return hasVisited;
    }

    public void setHasVisited(boolean hasVisited) {
        this.hasVisited = hasVisited;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void clearEnemies() {
        enemies.clear();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public static class Builder extends GameObject.Builder<Builder> {
        private final List<Item> items = new ArrayList<>();
        private final List<Enemy> enemies = new ArrayList<>();

        public Builder items(List<Item> items) {
            this.items.addAll(items);
            return this;
        }

        public Builder items(Item... items) {
            this.items.addAll(List.of(items));
            return this;
        }

        public Builder enemies(List<Enemy> enemies) {
            this.enemies.addAll(enemies);
            return this;
        }

        public Builder enemies(Enemy... enemies) {
            this.enemies.addAll(List.of(enemies));
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Room build() {
            return new Room(this);
        }
    }
}
