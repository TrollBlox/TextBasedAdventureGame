package game.adventure.adventures;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.util.Adventure;
import game.adventure.objects.ItemPair;
import game.adventure.util.AdventureContext;

public class AdventureTesting extends Adventure {
    private static final AdventureContext context = new AdventureContext();

    private static final Player player = new Player.Builder().build();

    @Override
    public void init() {
        context.registerItem("dull_sword", new Item.Builder().name("Dull Sword").description("a sword that's dull").damage(1).equippable(true));
        context.registerItem("rock", new Item.Builder().name("Rock").description("maybe this could sharpen something"));
        context.registerItem("sharp_sword", new Item.Builder().name("Sharp Sword").description("a newly sharpened sword").damage(5).equippable(true));

        context.registerRoom("chamber", new Room.Builder().name("Testing Chamber").description("test").items(context.getItem("rock"), context.getItem("dull_sword")));

        context.registerItemUse(new ItemPair(context.getItem("dull_sword"), context.getItem("rock")), () -> {
            player.removeItem(context.getItem("dull_sword"));
            player.removeItem(context.getItem("rock"));
            player.addItem(context.getItem("sharp_sword"));
            System.out.println("You used the rock to sharpen the sword.");
        });
    }

    @Override
    public Room getStartingRoom() {
        return context.getRoom("chamber");
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return "Adventure Testing";
    }

    @Override
    public AdventureContext getContext() {
        return context;
    }
}
