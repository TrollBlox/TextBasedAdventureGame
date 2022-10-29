package game.adventure.utils

import game.adventure.gameobjects.Enemy
import game.adventure.gameobjects.Item
import game.adventure.gameobjects.Player
import game.adventure.gameobjects.Room
import game.adventure.main.GameLogic

object Constants {
    val playerInventory: MutableList<Item> = ArrayList()
    @JvmField
    val player = Player(5, playerInventory, -1)
    @JvmField
    val map = arrayOfNulls<Room>(5)
    val tutorial1 = Item(
        "Page 1",
        "A paper you found in the Forest. It says: \"Welcome. Don't forget to take the Sword and equip it before you leave. You might need it...\"",
        true,
        -1
    )
    val tutorial2 = Item(
        "Page 2",
        "A paper you found in the Morgue. It says: \"You need to access the closet. The key is in the pen. Use the scalpel to open it...\"",
        true,
        -1
    )
    val sword = Item("Sword", "You don't know where it came from, but it will be useful anyway.", true, 5)
    val pen = Item("Pen", "You can hear something rattling around in it, but you can't get it open.", true, -1)
    val lockBox = Item("Lock Box", "It has a ridiculously small key hole.", true, -1)
    val scalpel = Item("Scalpel", "It was found on the embalming table. It will definitely be useful.", true, 3)
    val key = Item("Key", "You don't know how this got in the pen, but you have it now.", true, -1)
    val broom = Item("Broom", "You found it in the closet. That's everything about it.", true, -1)
    val doorLock = Item("Door Lock", "A lock on the east door in the morgue.", false, -1)
    val brokenPen = Item("Broken Pen", "A pen you cut open with the scalpel. It is very sharp.", true, 10)
    val rottenFlesh = Item("Rotten Flesh", "It might just be the worst thing you have ever smelled.", true, -1)
    val smallKey =
        Item("Small key", "A ridiculously small key that would only fit in a ridiculously small key hole.", true, -1)
    val tutorial3 = Item(
        "Page 3",
        "A paper you found in the Lock box. It says: \"That's the end of the tutorial. Feel free to add your own content by editing the utils folder in the project!\"",
        true,
        -1
    )
    val unlockBox = Item("Unlocked Lock Box", "An unlocked lock box. You just opened it.", true, -1)
    val forestItems: MutableList<Item> = ArrayList()
    val entryItems: MutableList<Item> = ArrayList()
    val hallItems: MutableList<Item> = ArrayList()
    val morgueItems: MutableList<Item> = ArrayList()
    val closetItems: MutableList<Item> = ArrayList()
    val forestEnemies: MutableList<Enemy> = ArrayList()
    val entryEnemies: MutableList<Enemy> = ArrayList()
    val hallEnemies: MutableList<Enemy> = ArrayList()
    val morgueEnemies: MutableList<Enemy> = ArrayList()
    val closetEnemies: MutableList<Enemy> = ArrayList()
    val zombieDrops: MutableList<Item> = ArrayList()
    val zombie = Enemy("Zombie", "A zombie in the Morgue!", 5, 1, zombieDrops)
    val forest = Room(
        "Forest",
        "The forest just outside of the hospital. It is very dark, with no crickets.",
        1,
        -1,
        -1,
        -1,
        forestItems,
        forestEnemies
    )
    val entry = Room(
        "Entryway",
        "The entryway to the hospital. There is no-one around, and everything is still a pristine white.",
        -1,
        0,
        2,
        -1,
        entryItems,
        entryEnemies
    )
    val hall = Room(
        "Hallway",
        "The main hallway in the hospital. There are many rooms with nothing of interest in them.",
        -1,
        3,
        -1,
        1,
        hallItems,
        hallEnemies
    )
    val morgue = Room(
        "Morgue",
        "A morgue, with smells that indicate it was never fully emptied before the zombies came...",
        2,
        -1,
        -1,
        -1,
        morgueItems,
        morgueEnemies
    )
    val closet = Room(
        "Closet",
        "A closet in the back of the Morgue. It is the only place in this wing of the hospital that doesn't smell like death...",
        -1,
        -1,
        -1,
        3,
        closetItems,
        closetEnemies
    )
    var useFail =
        Runnable { println("You can't use those items together. Maybe try putting them in the opposite order.") }
    var scalpelOnPen = Runnable {
        player.addItem(key)
        player.addItem(brokenPen)
        player.removeItem(pen)
        GameLogic.currentRoom.removeItem(pen)
        println("You found a Key inside of the Pen!")
    }
    var keyOnClosetDoor = Runnable {
        println("You unlocked the closet door!")
        morgue.removeItem(doorLock)
        morgue.e = 4
    }
    var smallKeyOnLockBox = Runnable {
        println("You found a piece of paper in the lock box!")
        GameLogic.currentRoom.removeItem(lockBox)
        player.removeItem(lockBox)
        GameLogic.currentRoom.addItem(unlockBox)
        player.addItem(tutorial3)
    }
}