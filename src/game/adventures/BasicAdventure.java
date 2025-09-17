package game.adventures;

import game.adventure.gameobjects.*;
import game.adventure.interfaces.Adventure;
import game.adventure.objects.DefaultHashMap;
import game.adventure.objects.ItemPair;

import java.util.ArrayList;
import java.util.List;

public class BasicAdventure implements Adventure {
    private final List<Item> playerInventory = new ArrayList<>();

    private final Player player = new Player(5, playerInventory, -1);
    private final List<Room> map = new ArrayList<>();

    private final Item tutorial1 = new Item("Page 1", "A paper you found in the Forest. It says: \"Welcome. Don't forget to take the Sword and equip it before you leave. You might need it...\"", true, -1);
    private final Item tutorial2 = new Item("Page 2", "A paper you found in the Morgue. It says: \"You need to access the closet. The key is in the pen. Use the scalpel to open it...\"", true, -1);

    private final Item sword = new Item("Sword", "You don't know where it came from, but it will be useful anyway.", true, 5);
    private final Item pen = new Item("Pen", "You can hear something rattling around in it, but you can't get it open.", true, -1);
    private final Item lockBox = new Item("Lock Box", "It has a ridiculously small key hole.", true, -1);
    private final Item scalpel = new Item("Scalpel", "It was found on the embalming table. It will definitely be useful.", true, 3);
    private final Item key = new Item("Key", "You don't know how this got in the pen, but you have it now.", true, -1);
    private final Item broom = new Item("Broom", "You found it in the closet. That's everything about it.", true, -1);
    private final Item doorLock = new Item("Door Lock", "A lock on the east door in the morgue.", false, -1);
    private final Item brokenPen = new Item("Broken Pen", "A pen you cut open with the scalpel. It is very sharp.", true, 10);
    private final Item rottenFlesh = new Item("Rotten Flesh", "It might just be the worst thing you have ever smelled.", true, -1);
    private final Item smallKey = new Item("Small key", "A ridiculously small key that would only fit in a ridiculously small key hole.", true, -1);
    private final Item tutorial3 = new Item("Page 3", "A paper you found in the Lock box. It says: \"That's the end of the tutorial. Feel free to add your own content by editing the utils folder in the project!\"", true, -1);
    private final Item unlockBox = new Item("Unlocked Lock Box", "An unlocked lock box. You just opened it.", true, -1);

    private final List<Item> forestItems = new ArrayList<>();
    private final List<Item> entryItems = new ArrayList<>();
    private final List<Item> hallItems = new ArrayList<>();
    private final List<Item> morgueItems = new ArrayList<>();
    private final List<Item> closetItems = new ArrayList<>();

    private final List<Enemy> morgueEnemies = new ArrayList<>();

    private final List<Item> zombieDrops = new ArrayList<>();

    private final Enemy zombie = new Enemy("Zombie", "A zombie in the Morgue!", 5, 1, zombieDrops);

    private final Room forest = new Room("Forest", "The forest just outside of the hospital. It is very dark, with no crickets.", "Entryway", "", "", "", forestItems, Item.class);
    private final Room entry = new Room("Entryway", "The entryway to the hospital. There is no-one around, and everything is still a pristine white.", "", "Forest", "Hallway", "", entryItems, Item.class);
    private final Room hall = new Room("Hallway", "The main hallway in the hospital. There are many rooms with nothing of interest in them.", "", "Morgue", "", "Entryway", hallItems, Item.class);
    private final Room morgue = new Room("Morgue", "A morgue, with smells that indicate it was never fully emptied before the zombies came...", "Hallway", "", "", "", morgueItems, morgueEnemies);
    private final Room closet = new Room("Closet", "A closet in the back of the Morgue. It is the only place in this wing of the hospital that doesn't smell like death...", "", "", "", "", closetItems, Item.class);

    private final Runnable useFail = () -> System.out.println("You can't use those items together. Maybe try putting them in the opposite order.");

    private final Runnable scalpelOnPen = () -> {
        getPlayer().removeItem(pen);
        player.addItem(key);
        player.addItem(brokenPen);
        System.out.println("You found a Key inside of the Pen!");
    };

    private final Runnable keyOnClosetDoor = () -> {
        morgue.removeItem(doorLock);
        morgue.setE("Closet");
        System.out.println("You unlocked the closet door!");
    };

    private final Runnable smallKeyOnLockBox = () -> {
        player.removeItem(lockBox);
        player.addItem(unlockBox);
        player.addItem(tutorial3);
        System.out.println("You found a piece of paper in the lock box!");
    };

    private final DefaultHashMap<ItemPair, Runnable> itemUses = new DefaultHashMap<>(useFail);
    
    @Override
    public void init() {
        forestItems.add(sword);
        entryItems.add(pen);
        hallItems.add(lockBox);
        morgueItems.add(scalpel);
        morgueItems.add(doorLock);
        closetItems.add(broom);
        closetItems.add(smallKey);
        forestItems.add(tutorial1);
        entryItems.add(tutorial2);

        zombieDrops.add(rottenFlesh);

        morgueEnemies.add(zombie);

        map.add(forest);
        map.add(entry);
        map.add(hall);
        map.add(morgue);
        map.add(closet);

        itemUses.put(new ItemPair(scalpel, pen), scalpelOnPen);
        itemUses.put(new ItemPair(key, doorLock), keyOnClosetDoor);
        itemUses.put(new ItemPair(smallKey, lockBox), smallKeyOnLockBox);
    }

    @Override
    public Room getStartingRoom() {
        return forest;
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
