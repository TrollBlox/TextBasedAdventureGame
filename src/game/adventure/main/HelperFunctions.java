package game.adventure.main;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Room;

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
        if (AdventureManager.getCurrentRoom().getN().isEmpty()) return;
        if (getMap(AdventureManager.getCurrentRoom().getN()).getHasVisited()) {
            System.out.println("To the north: " + getMap(AdventureManager.getCurrentRoom().getN()).getName());
            return;
        }
        System.out.println("There is something to the north.");
    }

    public static void lookSouth() {
        if (AdventureManager.getCurrentRoom().getS().isEmpty()) return;
        if (getMap(AdventureManager.getCurrentRoom().getS()).getHasVisited()) {
            System.out.println("To the south: " + getMap(AdventureManager.getCurrentRoom().getS()).getName());
            return;
        }
        System.out.println("There is something to the south.");
    }

    public static void lookEast() {
        if (AdventureManager.getCurrentRoom().getE().isEmpty()) return;
        if (getMap(AdventureManager.getCurrentRoom().getE()).getHasVisited()) {
            System.out.println("To the east: " + getMap(AdventureManager.getCurrentRoom().getE()).getName());
            return;
        }
        System.out.println("There is something to the east.");
    }

    public static void lookWest() {
        if (AdventureManager.getCurrentRoom().getW().isEmpty()) return;
        if (getMap(AdventureManager.getCurrentRoom().getW()).getHasVisited()) {
            System.out.println("To the west: " + getMap(AdventureManager.getCurrentRoom().getW()).getName());
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

    public static Room getMap(String name) {
        for (Room room : AdventureManager.getCurrentAdventure().getMap()) {
            if (room.getName().equals(name)) return room;
        }
        throw new RuntimeException("Unknown Room!");
    }

    public static boolean startsWithVowel(String string) {
        return switch (string.toLowerCase().charAt(0)) {
            case 'a', 'e', 'i', 'o', 'u', 'y' -> true;
            default -> false;
        };
    }

    public static void removeItem(Item item) {
        AdventureManager.getCurrentRoom().removeItem(item);
        AdventureManager.getCurrentAdventure().getPlayer().removeItem(item);
    }

    public static Item getItemFromName(String name) {
        for (Item item : AdventureManager.getPlayer().getInventory()) {
            if (item.isName(name)) return item;
        }
        for (Room room : AdventureManager.getCurrentAdventure().getMap()) {
            for (Item item : room.getItems()) {
                if (item.isName(name)) return item;
            }
        }
        throw new RuntimeException("Item not found!");
    }

}
