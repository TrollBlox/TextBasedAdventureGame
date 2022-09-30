package game.adventure.utils;

import game.adventure.gameobjects.Room;

public final class Utils {

    private Utils() { }

    public static Runnable useFetcher(String itemOneName, String itemTwoName) {
        if (itemOneName.equalsIgnoreCase("Scalpel") && itemTwoName.equalsIgnoreCase("Pen")) {
            return Constants.scalpelOnPen;
        } else if (itemOneName.equalsIgnoreCase("Key") && itemTwoName.equalsIgnoreCase("Door lock")) {
            return Constants.keyOnClosetDoor;
        } else if (itemOneName.equalsIgnoreCase("Small key") && itemTwoName.equalsIgnoreCase("Lock box")) {
            return Constants.smallKeyOnLockBox;
        }
        return Constants.useFail;
    }

}
