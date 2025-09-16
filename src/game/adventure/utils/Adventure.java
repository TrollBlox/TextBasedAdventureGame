package game.adventure.utils;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;

public interface Adventure {
    void init();

    Room getStartingRoom();

    Runnable useFetcher(Item item1, Item item2);

    Player getPlayer();
}
