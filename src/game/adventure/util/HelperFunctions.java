package game.adventure.util;

import game.adventure.gameobjects.Item;
import game.adventure.main.AdventureManager;
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

    public static void lookCardinal() {
        for (Direction direction : Direction.values()) {
            if (AdventureManager.getCurrentRoom().getDirection(direction).isEmpty()) return;
            if (AdventureManager.getContext().getRoom(AdventureManager.getCurrentRoom().getDirection(direction)).getHasVisited()) {
                System.out.println("To the " + direction.getString() +": " + AdventureManager.getContext().getRoom(AdventureManager.getCurrentRoom().getDirection(direction)).getName());
                return;
            }
            System.out.println("There is something to the " + direction.getString() + ".");
        }
    }

    public static String firstLetterUpper(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static boolean startsWithVowel(String string) {
        return switch (string.toLowerCase().charAt(0)) {
            case 'a', 'e', 'i', 'o', 'u', 'y' -> true;
            default -> false;
        };
    }

    public static Item getItemFromName(String name) {
        for (Item item : AdventureManager.getCurrentAdventure().getItems()) {
            if (item.isName(name)) return item;
        }
        throw new RuntimeException("Item not found!");
    }

}
