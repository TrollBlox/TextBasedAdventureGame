package game.adventure.main;

import game.adventure.utils.Adventure;
import game.adventure.utils.BasicAdventure;

public class AdventureManager {
    private static Adventure currentAdventure = new BasicAdventure();

    public static Adventure getCurrentAdventure() {
        return currentAdventure;
    }
}
