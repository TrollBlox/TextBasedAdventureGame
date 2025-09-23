package game.adventure.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private String name, description;
    private String n, s, e, w;
    private List<Item> items;
    private List<Enemy> enemies;
    private boolean hasVisited;

    public Room(String name, String description, String n, String s, String e, String w, List<Item> items, List<Enemy> enemies) {
        init(name, description, n, s, e, w, items, enemies);
    }

    public Room(String name, String description, String n, String s, String e, String w) {
        init(name, description, n, s, e, w, new ArrayList<>(), new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    public Room(String name, String description, String n, String s, String e, String w, List<?> itemsOrEnemies, Class<?> type) {
        List<Item> items = new ArrayList<>();
        List<Enemy> enemies = new ArrayList<>();
        if (type == Item.class) {
            items = (List<Item>) itemsOrEnemies;
        } else if (type == Enemy.class) {
            enemies = (List<Enemy>) itemsOrEnemies;
        } else {
            throw new IllegalArgumentException("Unsupported class type!");
        }
        init(name, description, n, s, e, w, items, enemies);
    }

    private void init(String name, String description, String n, String s, String e, String w, List<Item> items, List<Enemy> enemies) {
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

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
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

    public void addItems(Item... items) {
        this.items.addAll(Arrays.asList(items));
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
