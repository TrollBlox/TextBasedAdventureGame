package game.adventure.utils;

import game.adventure.main.HelperFunctions;

public class GameInit {

    public GameInit() {
        Constants.forestItems.add(Constants.sword);
        Constants.entryItems.add(Constants.pen);
        Constants.hallItems.add(Constants.lockBox);
        Constants.morgueItems.add(Constants.scalpel);
        Constants.morgueItems.add(Constants.doorLock);
        Constants.closetItems.add(Constants.broom);
        Constants.forestItems.add(Constants.tutorial1);
        Constants.entryItems.add(Constants.tutorial2);

        Constants.zombieDrops.add(Constants.rottenFlesh);

        Constants.morgueEnemies.add(Constants.zombie);

        HelperFunctions.setMap(Constants.forest, 0);
        HelperFunctions.setMap(Constants.entry, 1);
        HelperFunctions.setMap(Constants.hall, 2);
        HelperFunctions.setMap(Constants.morgue, 3);
        HelperFunctions.setMap(Constants.closet, 4);
    }

}
