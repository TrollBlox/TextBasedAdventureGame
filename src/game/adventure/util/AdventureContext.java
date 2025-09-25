package game.adventure.util;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Room;
import game.adventure.objects.ItemPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventureContext {
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, Enemy> enemies = new HashMap<>();
    private final Map<String, Room> rooms = new HashMap<>();
    private final Map<ItemPair, Runnable> itemUses = new HashMap<>();

    public void registerItem(String id, Item.Builder builder) {
        Item item = builder.build();
        items.put(id, item);
    }

    public void registerEnemy(String id, Enemy.Builder builder) {
        Enemy enemy = builder.build();
        enemies.put(id, enemy);
    }

    public void registerRoom(String id, Room.Builder builder) {
        Room room = builder.build();
        rooms.put(id, room);
    }

    public void registerItemUse(ItemPair pair, Runnable runnable) {
        itemUses.put(pair, runnable);
    }

    public Room getRoom(String id) {
        return rooms.get(id);
    }

    public Item getItem(String id) {
        return items.get(id);
    }

    public Enemy getEnemy(String id) {
        return enemies.get(id);
    }

    public List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public List<Enemy> getEnemies() {
        return new ArrayList<>(enemies.values());
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public Map<ItemPair, Runnable> getItemUses() {
        return itemUses;
    }

    public void connectRoom(String from, Direction dir, String to) {
        getRoom(from).setDirection(dir, to);
        getRoom(to).setDirection(dir.getOpposite(), from);
    }

}
