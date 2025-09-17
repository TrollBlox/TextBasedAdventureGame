package game.adventure.main;

import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.interfaces.Adventure;
import game.adventures.AdventureTesting;

public class AdventureManager {
    private static Adventure currentAdventure = new AdventureTesting(); // temp for testing
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
}
