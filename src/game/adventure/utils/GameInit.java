package game.adventure.utils;

public class GameInit {

    public GameInit() {
        Constants.forestItems.add(Constants.sword);
        Constants.entryItems.add(Constants.pen);
        Constants.hallItems.add(Constants.lockbox);
        Constants.morgueItems.add(Constants.scalpel);
        Constants.morgueItems.add(Constants.doorlock);
        Constants.closetItems.add(Constants.broom);
        Constants.forestItems.add(Constants.tutorial1);
        Constants.entryItems.add(Constants.tutorial2);

        Constants.zombieDrops.add(Constants.rottenFlesh);

        Constants.morgueEnemies.add(Constants.zombie);

        Utils.setMap(Constants.forest, 0);
        Utils.setMap(Constants.entry, 1);
        Utils.setMap(Constants.hall, 2);
        Utils.setMap(Constants.morgue, 3);
        Utils.setMap(Constants.closet, 4);
    }

}
