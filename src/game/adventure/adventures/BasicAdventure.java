package game.adventure.adventures;

import game.adventure.gameobjects.*;
import game.adventure.util.Adventure;
import game.adventure.objects.ItemPair;
import game.adventure.util.AdventureContext;
import game.adventure.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class BasicAdventure extends Adventure {
    private final AdventureContext context = new AdventureContext();

    private final Player player = new Player.Builder().health(5).build();
    
    @Override
    public void init() {
        context.registerItem("page_1", new Item.Builder().name("Page 1").description("A paper you found in the Forest. It says: \"Welcome. Don't forget to take the Sword and equip it before you leave. You might need it...\""));
        context.registerItem("page_2", new Item.Builder().name("Page 2").description("A paper you found in the Morgue. It says: \"You need to access the closet. The key is in the pen. Use the scalpel to open it...\""));
        context.registerItem("sword", new Item.Builder().name("Sword").description("You don't know where it came from, but it will be useful anyway.").equippable(true).damage(5));
        context.registerItem("pen", new Item.Builder().name("Pen").description("You can hear something rattling around in it, but you can't get it open."));
        context.registerItem("lock_box", new Item.Builder().name("Lock Box").description("It has a ridiculously small key hole."));
        context.registerItem("scalpel", new Item.Builder().name("Scalpel").description("It was found on the embalming table. It will definitely be useful.").equippable(true).damage(3));
        context.registerItem("key", new Item.Builder().name("Key").description("You don't know how this got in the pen, but you have it now."));
        context.registerItem("broom", new Item.Builder().name("Broom").description("You found it in the closet. That's everything about it."));
        context.registerItem("door_lock", new Item.Builder().name("Door Lock").description("A lock on the east door in the morgue.").canPickUp(false));
        context.registerItem("broken_pen", new Item.Builder().name("Broken Pen").description("A pen you cut open with the scalpel. It is very sharp.").equippable(true).damage(10));
        context.registerItem("rotten_flesh", new Item.Builder().name("Rotten Flesh").description("It might just be the worst thing you have ever smelled."));
        context.registerItem("small_key", new Item.Builder().name("Small Key").description("A ridiculously small key that would only fit in a ridiculously small key hole."));
        context.registerItem("page_3", new Item.Builder().name("Page 3").description("A paper you found in the Lock box. It says: \"That's the end of the tutorial. Feel free to add your own content by editing the utils folder in the project!\""));
        context.registerItem("unlocked_box", new Item.Builder().name("Unlocked Lock Box").description("An unlocked lock box. You just opened it."));

        context.registerEnemy("zombie", new Enemy.Builder().name("Zombie").description("A zombie in the Morgue!").health(5).damage(1).drops(context.getItem("rotten_flesh")));

        context.registerRoom("forest", new Room.Builder().name("Forest").description("The forest just outside of the hospital. It is very dark, with no crickets.").items(context.getItem("sword"), context.getItem("page_1")));
        context.registerRoom("entryway", new Room.Builder().name("Entryway").description("The entryway to the hospital. There is no-one around, and everything is still a pristine white.").items(context.getItem("pen"), context.getItem("page_2")));
        context.registerRoom("hallway", new Room.Builder().name("Hallway").description("The main hallway in the hospital. There are many rooms with nothing of interest in them.").items(context.getItem("lock_box")));
        context.registerRoom("morgue", new Room.Builder().name("Morgue").description("A morgue, with smells that indicate it was never fully emptied before the zombies came...").items(context.getItem("scalpel"), context.getItem("door_lock")).enemies(context.getEnemy("zombie")));
        context.registerRoom("closet", new Room.Builder().name("Closet").description("A closet in the back of the Morgue. It is the only place in this wing of the hospital that doesn't smell like death...").items(context.getItem("broom"), context.getItem("small_key")));

        context.connectRoom("forest", Direction.NORTH, "entryway");
        context.connectRoom("entryway", Direction.EAST, "hallway");
        context.connectRoom("hallway", Direction.SOUTH, "morgue");;

        context.registerItemUse(new ItemPair(context.getItem("scalpel"), context.getItem("pen")), () -> {
            player.removeItem(context.getItem("pen"));
            player.addItem(context.getItem("key"));
            player.addItem(context.getItem("broken_pen"));
            System.out.println("You found a Key inside of the Pen!");
        });
        context.registerItemUse(new ItemPair(context.getItem("key"), context.getItem("door_lock")), () -> {
            context.getRoom("morgue").removeItem(context.getItem("door_lock"));
            context.connectRoom("morgue", Direction.EAST, "closet");
            System.out.println("You unlocked the closet door!");
        });
        context.registerItemUse(new ItemPair(context.getItem("small_key"), context.getItem("lock_box")), () -> {
            player.removeItem(context.getItem("lock_box"));
            player.addItem(context.getItem("unlock_box"));
            player.addItem(context.getItem("page_3"));
            System.out.println("You found a piece of paper in the lock box!");
        });
    }

    @Override
    public Room getStartingRoom() {
        return context.getRoom("forest");
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return "Basic Adventure";
    }

    @Override
    public AdventureContext getContext() {
        return context;
    }
}
