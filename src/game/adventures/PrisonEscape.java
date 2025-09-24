package game.adventures;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.interfaces.Adventure;
import game.adventure.objects.DefaultHashMap;
import game.adventure.objects.ItemPair;
import java.util.ArrayList;
import java.util.List;

public class PrisonEscape implements Adventure {
    private static final List<Item> items = new ArrayList<>();
    private static final List<Room> map = new ArrayList<>();
    private static final Player player = new Player(100, new ArrayList<>(), -1);
    private static final Runnable defaultUse = () -> System.out.println("You can't use those items together!");
    private static final DefaultHashMap<ItemPair, Runnable> itemUses = new DefaultHashMap<>(defaultUse);
    private static final Room cell = new Room("Cell", "The cell you are kept in until you can escape.", "", "", "", "");
    private static final Room wingA = new Room("Wing A", "Wing A of the prison", "Cell", "", "", "");
    private static final Item toilet = new Item("Toilet", "The toilet in the cell. It doesn't seem like it will be useful.", false, -1);
    private static final Item cabinet = new Item("Cabinet", "A locked cabinet hanging on the wall. If only you had the key...", false, -1);
    private static final Item multipurposeTool = new Item("Tool", "A Multi-purpose Tool you found in the cell. This should be useful.", true, -1);
    private static final Item apple = new Item("Apple", "An apple sitting on a shelf in the cell.", true, -1);
    private static final Item toiletPaper = new Item("Toilet Paper", "A roll of toilet paper sitting on a shelf in the cell.", true, -1);
    private static final Item cabinetKey = new Item("Key", "A key you found inside the apple", true, -1);
    private static final Item badge = new Item("Badge", "An electronic guard badge for the prison. You would not want to get caught with this.", true, -1);
    private static final Item water = new Item("Water", "Toilet water on the floor from the toilet overflowing.", false, -1);
    private static final Item pillow = new Item("Pillow", "The pillow sitting on the bed. It seems a bit lumpy.", false, -1);
    private static final Item uniform = new Item("Uniform", "A generic uniform. It's almost convincing enough to be a disguise.", true, 0);
    private static final Item guardUniform = new Item("Guard Uniform", "A Guard Uniform with an electronic badge. This could be a good disguise.", true, 0);
    private static final Runnable toolOnApple = () -> {
        player.removeItem(apple);
        player.addItem(cabinetKey);
        System.out.println("You cut open the apple and found a key inside!");
    };
    private static final Runnable keyOnCabinet = () -> {
        player.addItem(badge);
        cabinet.setDescription("An unlocked cabinet hanging on the wall. There's nothing inside.");
        System.out.println("You unlock the cabinet. Inside is a guard badge.");
    };
    private static final Runnable toiletPaperOnToilet = () -> {
        cell.addItem(water);
        toilet.setDescription("The toilet in the cell. It is overflowing after you tried to flush a roll of toilet paper.");
        System.out.println("The water in the toilet overflows. A guard comes to your cell to clean up the mess.");
        if (player.getEquipItem().equals(guardUniform)) {
            System.out.println("Guard: Oh, I didn't realize you were already coming to clean up the mess. I'll leave the cell unlocked so you aren't locked in here next time.");
            player.removeItem(toiletPaper);
            cell.setS("Wing A");
        } else {
            System.out.println("Guard: Why would you overflow the toilet?! Here's a new roll of toilet paper, don't do it again!");
        }
    };
    private static final Runnable toolOnPillow = () -> {
        pillow.setDescription("The pillow sitting on the bed. It has a large cut from you opening it.");
        System.out.println("Inside the pillow you find a uniform. It's almost convincing enough to be a disguise.");
        player.addItem(uniform);
    };
    private static final Runnable badgeOnUniform = () -> {
        System.out.println("You put the badge on the uniform. You now have a convincing guard uniform.");
        player.removeItem(uniform);
        player.removeItem(badge);
        player.addItem(guardUniform);
    };

    @Override
    public void init() {
        System.out.println("You are a prisoner. You must escape out the cell door, but not without a disguise...");
        map.add(cell);
        map.add(wingA);
        cell.addItems(toilet, pillow, cabinet, multipurposeTool, apple, toiletPaper);
        itemUses.put(new ItemPair(multipurposeTool, apple), toolOnApple);
        itemUses.put(new ItemPair(cabinetKey, cabinet), keyOnCabinet);
        itemUses.put(new ItemPair(toiletPaper, toilet), toiletPaperOnToilet);
        itemUses.put(new ItemPair(multipurposeTool, pillow), toolOnPillow);
        itemUses.put(new ItemPair(badge, uniform), badgeOnUniform);
        items.addAll(List.of(toilet, cabinet, multipurposeTool, apple, toiletPaper, cabinetKey, badge, water, pillow, uniform, guardUniform));
    }

    @Override
    public Room getStartingRoom() {
        return cell;
    }

    @Override
    public DefaultHashMap<ItemPair, Runnable> useFetcher() {
        return itemUses;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Room> getMap() {
        return map;
    }

    @Override
    public String getName() {
        return "Prison Escape";
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
