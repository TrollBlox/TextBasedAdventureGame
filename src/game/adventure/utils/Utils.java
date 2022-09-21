package game.adventure.utils;

import game.adventure.gameobjects.Room;

public final class Utils {

    private Utils() { }

    public static boolean startsWithVowel(String string) {
        return switch (string.toLowerCase().charAt(0)) {
            case 'a', 'e', 'i', 'o', 'u', 'y' -> true;
            default -> false;
        };
    }

    public static String firstLetterUpper(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static Runnable useFetcher(String itemOneName, String itemTwoName) {
        if (itemOneName.equals("Scalpel") && itemTwoName.equals("Pen")) {
            return Constants.scalpelOnPen;
        } else if (itemOneName.equals("Key") && itemTwoName.equals("Door lock")) {
            return Constants.keyOnClosetDoor;
        }
        return Constants.useFail;
    }

    public static Room getMap(int i) {
        return Constants.map[i];
    }

    public static void setMap(Room newRoom, int i) {
        Constants.map[i] = newRoom;
    }

}
