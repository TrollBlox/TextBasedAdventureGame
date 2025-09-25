package game.adventure.adventures;

import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Player;
import game.adventure.gameobjects.Room;
import game.adventure.util.Adventure;
import game.adventure.objects.ItemPair;
import game.adventure.util.AdventureContext;
import game.adventure.util.Direction;

public class PrisonEscape extends Adventure {
    private static final AdventureContext context = new AdventureContext();

    private static final Player player = new Player.Builder().health(100).build();

    @Override
    public void init() {
        context.registerItem("toilet", new Item.Builder().name("Toilet").description("The toilet in the cell. It doesn't seem like it will be useful.").canPickUp(false));
        context.registerItem("cabinet", new Item.Builder().name("Cabinet").description("A locked cabinet hanging on the wall. If only you had the key...").canPickUp(false));
        context.registerItem("knife", new Item.Builder().name("Knife").description("A knife you found in the cell. This should be useful."));
        context.registerItem("apple", new Item.Builder().name("Apple").description("An apple sitting on a shelf in the cell."));
        context.registerItem("toilet_paper", new Item.Builder().name("Toilet Paper").description("A roll of toilet paper sitting on a shelf in the cell."));
        context.registerItem("key", new Item.Builder().name("Key").description("A key you found inside the apple"));
        context.registerItem("badge", new Item.Builder().name("Badge").description("An electronic guard badge for the prison. You would not want to get caught with this."));
        context.registerItem("water", new Item.Builder().name("Water").description("Toilet water on the floor from the toilet overflowing.").canPickUp(false));
        context.registerItem("pillow", new Item.Builder().name("Pillow").description("The pillow sitting on the bed. It seems a bit lumpy.").canPickUp(false));
        context.registerItem("uniform", new Item.Builder().name("Uniform").description("A generic uniform. It's *almost* convincing enough to be a disguise.").equippable(true));
        context.registerItem("guard_uniform", new Item.Builder().name("Guard Uniform").description("A Guard Uniform with an electronic badge. This could be a good disguise.").equippable(true));

        context.registerRoom("cell", new Room.Builder().name("Cell").description("The cell you are kept in until you can escape.")
                .items(context.getItem("toilet"), context.getItem("pillow"), context.getItem("cabinet"),
                        context.getItem("knife"), context.getItem("toilet_paper"), context.getItem("apple")));
        context.registerRoom("wing_a", new Room.Builder().name("Wing A").description("Wing A of the prison"));

        System.out.println("You are a prisoner. You must escape out the cell door, but not without a disguise...");

        context.registerItemUse(new ItemPair(context.getItem("knife"), context.getItem("apple")), () -> {
            player.removeItem(context.getItem("apple"));
            player.addItem(context.getItem("key"));
            System.out.println("You cut open the apple and found a key inside!");
        });
        context.registerItemUse(new ItemPair(context.getItem("key"), context.getItem("cabinet")), () -> {
            player.addItem(context.getItem("badge"));
            context.getItem("cabinet").setDescription("An unlocked cabinet hanging on the wall. There's nothing inside.");
            System.out.println("You unlock the cabinet. Inside is a guard badge.");
        });
        context.registerItemUse(new ItemPair(context.getItem("toilet_paper"), context.getItem("toilet")), () -> {
            if (context.getRoom("cell").hasItem(context.getItem("water"))) context.getRoom("cell").addItem(context.getItem("water"));
            context.getItem("toilet").setDescription("The toilet in the cell. It is overflowing after you tried to flush a roll of toilet paper.");
            System.out.println("The water in the toilet overflows. A guard comes to your cell to clean up the mess.");
            if (player.getEquip().equals(context.getItem("guard_uniform"))) {
                System.out.println("Guard: Oh, I didn't realize you were already coming to clean up the mess. I'll leave the cell unlocked so you aren't locked in here next time.");
                player.removeItem(context.getItem("toilet_paper"));
                context.connectRoom("cell", Direction.SOUTH, "wing_a");
            } else {
                System.out.println("Guard: Why would you overflow the toilet?! Here's a new roll of toilet paper, don't do it again!");
            }
        });
        context.registerItemUse(new ItemPair(context.getItem("knife"), context.getItem("pillow")), () -> {
            context.getItem("pillow").setDescription("The pillow sitting on the bed. It has a large cut from you opening it.");
            System.out.println("Inside the pillow you find a uniform. It's almost convincing enough to be a disguise.");
            player.addItem(context.getItem("uniform"));
        });
        context.registerItemUse(new ItemPair(context.getItem("badge"), context.getItem("uniform")), () -> {
            System.out.println("You put the badge on the uniform. You now have a convincing guard uniform.");
            player.removeItem(context.getItem("uniform"));
            player.removeItem(context.getItem("badge"));
            player.addItem(context.getItem("guard_uniform"));
        });
    }

    @Override
    public Room getStartingRoom() {
        return context.getRoom("cell");
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return "Prison Escape";
    }

    @Override
    public AdventureContext getContext() {
        return context;
    }
}
