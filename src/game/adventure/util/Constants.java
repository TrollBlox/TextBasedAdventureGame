package game.adventure.util;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Room;

public class Constants {
    private Constants() { }

    public static final Item GENERIC_ITEM = new Item.Builder().build();
    public static final Room GENERIC_ROOM = new Room.Builder().build();
    public static final Enemy GENERIC_ENEMY = new Enemy.Builder().build();
}
