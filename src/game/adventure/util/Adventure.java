package game.adventure.util;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.objects.ItemPair;
import java.util.List;
import java.util.Map;

public abstract class Adventure {

    public abstract void init();

    public abstract Room getStartingRoom();

    public Map<ItemPair, Runnable> useFetcher() {
        return getContext().getItemUses();
    }

    public abstract Player getPlayer();

    public abstract String getName();

    public Room getRoom(String id) {
        return getContext().getRoom(id);
    }

    public List<Item> getItems() {
        return getContext().getItems();
    }

    public List<Enemy> getEnemies() {
        return getContext().getEnemies();
    }

    public List<Room> getRooms() {
        return getContext().getRooms();
    }

    public abstract AdventureContext getContext();
}
