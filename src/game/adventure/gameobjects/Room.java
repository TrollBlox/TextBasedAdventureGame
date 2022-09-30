package game.adventure.gameobjects;

import java.util.List;

public class Room {
    private String name, description;
    private int n, s, e, w;
    private final List<Item> items;
    private boolean hasVisited;

    private List<Enemy> enemies;

    public Room(String name, String description, int n, int s, int e, int w, List<Item> items, List<Enemy> enemies) {
        this.name = name;
        this.description = description;
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
        this.items = items;
        this.enemies = enemies;
        this.hasVisited = false;
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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
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

    public void removeItem(int index) {
        items.remove(index);
    }

    public void removeItem(String item) throws IndexOutOfBoundsException {
        int index = getInt(item);
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        items.remove(getItem(index));
    }

    public void clearItems() {
        items.clear();
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    private int getInt(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public Item getItem(String item) throws IndexOutOfBoundsException {
        int i = getInt(item);
        if (i == -1) {
            throw new IndexOutOfBoundsException();
        }
        return getItems().get(i);
    }

    public boolean hasItem(String item) {
        return getInt(item) != -1;
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

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

}
