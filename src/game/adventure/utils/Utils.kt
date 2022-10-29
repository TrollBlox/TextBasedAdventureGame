package game.adventure.utils

object Utils {
    @JvmStatic
    fun useFetcher(itemOneName: String, itemTwoName: String): Runnable {
        if (itemOneName.equals("Scalpel", ignoreCase = true) && itemTwoName.equals("Pen", ignoreCase = true)) {
            return Constants.scalpelOnPen
        } else if (itemOneName.equals("Key", ignoreCase = true) && itemTwoName.equals("Door lock", ignoreCase = true)) {
            return Constants.keyOnClosetDoor
        } else if (itemOneName.equals("Small key", ignoreCase = true) && itemTwoName.equals(
                "Lock box",
                ignoreCase = true
            )
        ) {
            return Constants.smallKeyOnLockBox
        }
        return Constants.useFail
    }
}