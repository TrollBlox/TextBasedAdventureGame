package game.adventure.utils;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;

import java.util.ArrayList;
import java.util.List;

public final class Constants {

    private Constants() { }

    public static final List<Item> playerInventory = new ArrayList<>();

    public static final Player player = new Player(5, playerInventory, -1);
    public static final Room[] map = new Room[5];

    public static final Item tutorial1 = new Item("Page 1", "A paper you found in the forest. It says: \"Welcome. Don't forget to take the Sword and equip it before you leave. You might need it...\"", true, -1);
    public static final Item tutorial2 = new Item("Page 2", "A paper you found in the Morgue. It says: \"You need to access the closet. The key is in the pen. Use the scalpel to open it...\"", true, -1);

    public static final Item sword = new Item("Sword", "You don't know where it came from, but it will be useful anyway.", true, 5);
    public static final Item pen = new Item("Pen", "You can hear something rattling around in it, but you can't get it open.", true, -1);
    public static final Item lockbox = new Item("Lockbox", "You can tell that whatever is inside is very important, but it is sadly locked.", true, -1);
    public static final Item scalpel = new Item("Scalpel", "It was found on the embalming table. It will definitely be useful.", true, -1);
    public static final Item key = new Item("Key", "You don't know how this got in the pen, but you have it now.", true, -1);
    public static final Item broom = new Item("Broom", "You found it in the closet. That's everything about it.", true, -1);
    public static final Item doorlock = new Item("Door lock", "A lock on the east door in the morgue.", false, -1);

    public static final Item brokenPen = new Item("Broken pen", "A pen you cut open with the scalpel. It is very sharp.", true, 10);

    public static final Item rottenFlesh = new Item("Rotten flesh", "It might just be the worst thing you have ever smelled.", true, -1);

    public static final List<Item> forestItems = new ArrayList<>();
    public static final List<Item> entryItems = new ArrayList<>();
    public static final List<Item> hallItems = new ArrayList<>();
    public static final List<Item> morgueItems = new ArrayList<>();
    public static final List<Item> closetItems = new ArrayList<>();

    public static final List<Enemy> forestEnemies = new ArrayList<>();
    public static final List<Enemy> entryEnemies = new ArrayList<>();
    public static final List<Enemy> hallEnemies = new ArrayList<>();
    public static final List<Enemy> morgueEnemies = new ArrayList<>();
    public static final List<Enemy> closetEnemies = new ArrayList<>();
    public static final List<Item> zombieDrops = new ArrayList<>();

    public static final Enemy zombie = new Enemy("Zombie", "A zombie in the Morgue!", 5, 1, zombieDrops);

    public static final Room forest = new Room("Forest", "The forest just outside of the hospital. It is very dark, with no crickets.", 1, -1, -1, -1, forestItems, forestEnemies);
    public static final Room entry = new Room("Entryway", "The entryway to the hospital. There is no-one around, and everything is still a pristine white.", -1, 0, 2, -1, entryItems, entryEnemies);
    public static final Room hall = new Room("Hallway", "The main hallway in the hospital. There are many rooms with nothing of interest in them.", -1, 3, -1, 1, hallItems, hallEnemies);
    public static final Room morgue = new Room("Morgue", "A morgue, with smells that indicate it was never fully emptied before the zombies came...", 2, -1, -1, -1, morgueItems, morgueEnemies);
    public static final Room closet = new Room("Closet", "A closet in the back of the Morgue. It is the only place in this wing of the hospital that doesn't smell like death...", -1, -1, -1, 3, closetItems, closetEnemies);



    public static Runnable useFail = new Runnable() {
        @Override
        public void run() {
            System.out.println("You can't use those items together. Maybe try putting them in the opposite order.");
        }
    };

    public static Runnable scalpelOnPen = new Runnable() {
        @Override
        public void run() {
            Constants.player.addItem(Constants.key);
            Constants.player.addItem(Constants.brokenPen);
            Constants.player.removeItem(Constants.pen);
            System.out.println("You found a Key inside of the Pen!");
        }
    };

    public static Runnable keyOnClosetDoor = new Runnable() {
        @Override
        public void run() {
            System.out.println("You unlocked the closet door!");
            Constants.morgue.removeItem("Door_lock");
            Constants.morgue.setE(4);
        }
    };

}
