package game.adventure.interfaces;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.objects.DefaultHashMap;
import game.adventure.objects.ItemPair;
import java.util.List;

public interface Adventure {
    void init();

    Room getStartingRoom();

    DefaultHashMap<ItemPair, Runnable> useFetcher();

    Player getPlayer();

    List<Room> getMap();

    String getName();

    List<Item> getItems();
}
