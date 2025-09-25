package game.adventure.main;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.util.Adventure;
import game.adventure.util.AdventureContext;

import java.util.List;

public class AdventureManager {
    private static Adventure currentAdventure; // temp for testing
    private static Room currentRoom;

    public static Adventure getCurrentAdventure() {
        return currentAdventure;
    }

    public static Room getCurrentRoom() {
        return currentRoom == null ? currentAdventure.getStartingRoom() : currentRoom;
    }

    public static void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public static Player getPlayer() {
        return currentAdventure.getPlayer();
    }

    public static void setCurrentAdventure(Adventure adventure) {
        currentAdventure = adventure;
    }

    public static AdventureContext getContext() {
        return currentAdventure.getContext();
    }

    public static List<Item> getItems() {
        return getContext().getItems();
    }

    public static List<Enemy> getEnemies() {
        return getContext().getEnemies();
    }

    public static List<Room> getRooms() {
        return getContext().getRooms();
    }
}
