package game.adventure.utils;

import game.adventure.gameobjects.Room;

public final class Utils {

    private Utils() { }

    public static Runnable useFetcher(String itemOneName, String itemTwoName) {
        if (itemOneName.equals("Scalpel") && itemTwoName.equals("Pen")) {
            return Constants.scalpelOnPen;
        } else if (itemOneName.equals("Key") && itemTwoName.equals("Door lock")) {
            return Constants.keyOnClosetDoor;
        } else if (itemOneName.equals("Small key") && itemTwoName.equals("Lock box")) {
            return Constants.smallKeyOnLockBox;
        }
        return Constants.useFail;
    }

}
