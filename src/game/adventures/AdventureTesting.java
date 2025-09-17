package game.adventures;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.interfaces.Adventure;
import game.adventure.objects.DefaultHashMap;
import game.adventure.objects.ItemPair;

import java.util.ArrayList;
import java.util.List;

public class AdventureTesting implements Adventure {
    private static final Player player = new Player(100, new ArrayList<>(), -1);
    private static final List<Room> map = new ArrayList<>();
    private static final Room room = new Room("Testing Chamber", "test", "", "", "", "");
    private static final Item dullSword = new Item("Dull Sword", "a sword thats dull", true, 0);
    private static final Item rock = new Item("Rock", "maybe this could sharpen something", true, -1);
    private static final Item sharpSword = new Item("Sharp Sword", "a newly sharpened sword", true, 5);
    private static final Runnable defaultUse = () -> System.out.println("You can't use those items together. Maybe try putting them in the opposite order.");
    private static final Runnable rockOnSword = () -> {
        player.removeItem(dullSword);
        player.removeItem(rock);
        player.addItem(sharpSword);
        System.out.println("You sharpened the sword!");
    };
    private static final DefaultHashMap<ItemPair, Runnable> itemUses = new DefaultHashMap<>(defaultUse);

    @Override
    public void init() {
        map.add(room);
        itemUses.put(new ItemPair(dullSword, rock), rockOnSword);
        player.addItem(dullSword);
        player.addItem(rock);
    }

    @Override
    public Room getStartingRoom() {
        return room;
    }

    @Override
    public DefaultHashMap<ItemPair, Runnable> useFetcher() {
        return itemUses;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Room> getMap() {
        return map;
    }
}
