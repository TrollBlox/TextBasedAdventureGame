package game.adventure.main;

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
        if (Utils.getMap(GameLogic.currentRoom.getN()).getHasVisited()) {
            System.out.println("To the north: " + Utils.getMap(GameLogic.currentRoom.getN()).getName());
            return;
        }
        System.out.println("There is something to the north.");
    }

    public static void lookSouth() {
        if (GameLogic.currentRoom.getS() == -1) return;
        if (Utils.getMap(GameLogic.currentRoom.getN()).getHasVisited()) {
            System.out.println("To the south: " + Utils.getMap(GameLogic.currentRoom.getS()).getName());
            return;
        }
        System.out.println("There is something to the south.");
    }

    public static void lookEast() {
        if (GameLogic.currentRoom.getE() == -1) return;
        if (Utils.getMap(GameLogic.currentRoom.getE()).getHasVisited()) {
            System.out.println("To the east: " + Utils.getMap(GameLogic.currentRoom.getE()).getName());
            return;
        }
        System.out.println("There is something to the east.");
    }

    public static void lookWest() {
        if (GameLogic.currentRoom.getW() == -1) return;
        if (Utils.getMap(GameLogic.currentRoom.getW()).getHasVisited()) {
            System.out.println("To the west: " + Utils.getMap(GameLogic.currentRoom.getW()).getName());
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
}
