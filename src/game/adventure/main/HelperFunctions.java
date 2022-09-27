package game.adventure.main;

import game.adventure.gameobjects.Room;
import game.adventure.utils.Constants;
import game.adventure.utils.Utils;

import java.util.List;

public class HelperFunctions {

    private HelperFunctions() { }

    public static String parseItemName(List<String> wordList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < wordList.size(); i++) {
            builder.append(wordList.get(i));
            builder.append(" ");
        }

        return builder.toString().trim();
    }

    public static void lookNorth() {
        if (GameLogic.currentRoom.getN() == -1) return;
        if (getMap(GameLogic.currentRoom.getN()).getHasVisited()) {
            System.out.println("To the north: " + getMap(GameLogic.currentRoom.getN()).getName());
            return;
        }
        System.out.println("There is something to the north.");
    }

    public static void lookSouth() {
        if (GameLogic.currentRoom.getS() == -1) return;
        if (getMap(GameLogic.currentRoom.getS()).getHasVisited()) {
            System.out.println("To the south: " + getMap(GameLogic.currentRoom.getS()).getName());
            return;
        }
        System.out.println("There is something to the south.");
    }

    public static void lookEast() {
        if (GameLogic.currentRoom.getE() == -1) return;
        if (getMap(GameLogic.currentRoom.getE()).getHasVisited()) {
            System.out.println("To the east: " + getMap(GameLogic.currentRoom.getE()).getName());
            return;
        }
        System.out.println("There is something to the east.");
    }

    public static void lookWest() {
        if (GameLogic.currentRoom.getW() == -1) return;
        if (getMap(GameLogic.currentRoom.getW()).getHasVisited()) {
            System.out.println("To the west: " + getMap(GameLogic.currentRoom.getW()).getName());
            return;
        }
        System.out.println("There is something to the west.");
    }

    public static void lookCardinal() {
        lookNorth();
        lookSouth();
        lookEast();
        lookWest();
    }

    public static String firstLetterUpper(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static Room getMap(int i) {
        return Constants.map[i];
    }

    public static void setMap(Room newRoom, int i) {
        Constants.map[i] = newRoom;
    }

    public static boolean startsWithVowel(String string) {
        return switch (string.toLowerCase().charAt(0)) {
            case 'a', 'e', 'i', 'o', 'u', 'y' -> true;
            default -> false;
        };
    }
}
