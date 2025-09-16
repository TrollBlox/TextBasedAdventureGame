package game.adventure.utils;

import game.adventure.main.GameLogic;
import game.adventure.main.HelperFunctions;

public class GameInit {

    public GameInit() {

        // Set initial Room
        GameLogic.currentRoom = HelperFunctions.getMap("Forest");
    }

}
