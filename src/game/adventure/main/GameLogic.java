package game.adventure.main;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.objects.ItemPair;
import game.adventure.util.Constants;
import game.adventure.util.Direction;
import game.adventure.util.HelperFunctions;

import java.util.*;

public class GameLogic {
    private static final Scanner s = new Scanner(System.in);

    private GameLogic() { }

    public static void start() {
        AdventureManager.getCurrentAdventure().init();
        takeInput();
    }

    private static void takeInput() {
        String input;
        System.out.println("Press h for help!");

        String n = HelperFunctions.startsWithVowel(AdventureManager.getCurrentRoom().getName()) ? "n " : " ";

        System.out.println("You find yourself in a" + n + AdventureManager.getCurrentRoom().getName());
        do {
            AdventureManager.getCurrentRoom().setHasVisited(true);
            System.out.print("> ");
            input = s.nextLine().toLowerCase();
            ParseCommand(input);
        } while (!input.equals("q") && !input.equals("quit"));
    }

    private static void ParseCommand(String input) {
        List<String> wordList;
        wordList = wordList(input);

        try {
            wordList.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must enter a command!");
            return;
        }

        switch (wordList.get(0)) {
            case "n":
            case "north":
            case "s":
            case "south":
            case "e":
            case "east":
            case "w":
            case "west":
                getDirection(wordList);
                break;
            case "i":
            case "inv":
            case "inventory":
                inventoryCommand();
                break;
            case "h":
            case "help":
                helpCommand();
                break;
            case "l":
            case "look":
                lookCommand(wordList);
                break;
            case "t":
            case "take":
                takeCommand(wordList);
                break;
            case "d":
            case "drop":
                dropCommand(wordList);
                break;
            case "u":
            case "use":
                useCommand(wordList);
                break;
            case "eq":
            case "equip":
                equipCommand(wordList);
                break;
            case "q":
            case "quit":
                break;
            default:
                System.out.println("I don't understand " + wordList.get(0));
                break;
        }
    }

    static void playerDeath(String killer) {
        System.out.println("The " + killer + " killed you!");
        System.out.println("rip bozo");
        System.exit(0);
    }

    private static void equipCommand(List<String> wordList) {
        if (wordList.size() == 1) {
            AdventureManager.getPlayer().removeEquip();
            System.out.println("Removed your equip");
            return;
        }

        String o = HelperFunctions.parseItemName(wordList);

        if (!AdventureManager.getPlayer().hasItem(o)) {
            System.out.println("You don't have the item " + o + "!");
            return;
        }

        if (!AdventureManager.getPlayer().getItem(o).isEquippable()) {
            System.out.println("You can't equip " + o + "!");
            return;
        }

        AdventureManager.getPlayer().setEquip(o);
        System.out.println("Equipped " + o);
    }

    private static void inspectItem(List<String> wordList) {
        String o = HelperFunctions.parseItemName(wordList);

        for (Item i : AdventureManager.getCurrentRoom().getItems()) {
            if (i.getName().equalsIgnoreCase(o)) {
                System.out.println(i.getName() + ": " + i.getDescription());
                return;
            }
        }
        for (Item i : AdventureManager.getPlayer().getInventory()) {
            if (i.getName().equalsIgnoreCase(o)) {
                System.out.println(i.getName() + ": " + i.getDescription());
                return;
            }
        }
        for (Enemy e : AdventureManager.getCurrentRoom().getEnemies()) {
            if (e.getName().equalsIgnoreCase(o)) {
                System.out.println(e.getName() + " (" + e.getHealth() + " hp " + e.getDamage() + " damage): " + e.getDescription());
                return;
            }
        }
        System.out.println("I don't know what " + o + " is!");
    }

    private static void helpCommand() {
        System.out.println("""
                Quit - "q"
                Look - "l" or "l <item>"
                Inventory - "i"
                Take - "t <item>"
                Drop - "d <item>"
                Equip - "eq <item>"
                Use - "u <item1> on <item2>"
                Movement - "n", "s", "e", and "w\"""");
    }

    private static void lookItemsCommand() {
        if (AdventureManager.getCurrentRoom().getItems().isEmpty()) {
            System.out.println("There are no items in this room!");
            return;
        }

        System.out.print("Items near you: ");
        for (int i = 0; i < AdventureManager.getCurrentRoom().getItems().size(); i++) {
            String separator = i == AdventureManager.getCurrentRoom().getItems().size() - 1 ? "." : ", ";

            System.out.print(AdventureManager.getCurrentRoom().getItem(i).getName() + separator);
        }
        System.out.println();

    }

    private static void lookCommand(List<String> wordList) {
        if (wordList.size() > 1) {
            inspectItem(wordList);
            return;
        }

        System.out.println(AdventureManager.getCurrentRoom().getName() + ": " + AdventureManager.getCurrentRoom().getDescription());

        lookItemsCommand();

        HelperFunctions.lookCardinal();

        if (AdventureManager.getCurrentRoom().getEnemies().isEmpty()) return;

        for (int i = 0; i < AdventureManager.getCurrentRoom().getEnemies().size(); i++) {
            Enemy e = AdventureManager.getCurrentRoom().getEnemies().get(i);
            System.out.println("Enemies near you: ");
            System.out.println(e.getName() + ": " + e.getDescription());
        }
    }

    private static void getInventory() {
        if (AdventureManager.getPlayer().getInventory().isEmpty() || (AdventureManager.getPlayer().getInventory().size() == 1 && !AdventureManager.getPlayer().getEquip().equals(Constants.GENERIC_ITEM))) {
            System.out.println("You have nothing in your inventory!");
            return;
        }

        System.out.println("Your inventory: ");
        for (int i = 0; i < AdventureManager.getPlayer().getInventory().size(); i++) {
            String nameSuffix = AdventureManager.getPlayer().getItem(i).getDamage() == -1 ? ": " : " (" + AdventureManager.getPlayer().getItem(i).getDamage() + " damage): ";

            System.out.println(AdventureManager.getPlayer().getItem(i).getName() + nameSuffix + AdventureManager.getPlayer().getItem(i).getDescription());
        }
    }

    private static void inventoryCommand() {
        System.out.println("Health: " + AdventureManager.getPlayer().getHealth());
        int damage = AdventureManager.getPlayer().getDamage();
        System.out.println("Damage: " + damage);
        System.out.println();

        getInventory();

        if (AdventureManager.getPlayer().hasEquip()) {
            System.out.println("\nEquipped item: \n" + AdventureManager.getPlayer().getEquip().getName() + " (" + AdventureManager.getPlayer().getDamage() + " damage): " + AdventureManager.getPlayer().getEquip().getDescription());
        }
    }

    private static List<String> wordList(String input) {
        String delims = "\s\t,.:;?!\"'";
        List<String> strList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delims);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strList.add(t);
        }

        return strList;
    }

    private static void useCommand(List<String> wordList) {
        String o, oo;

        if (wordList.size() < 4) {
            System.out.println("Not enough parameters!");
            return;
        }

        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < wordList.size(); i++) {
                if (wordList.get(i).equals("on")) {
                    wordList.remove(i);
                    break;
                }

                builder.append(wordList.get(i));
                builder.append(" ");
                wordList.remove(i);
                i--;
            }

            o = builder.toString().trim();

            oo = HelperFunctions.parseItemName(wordList);

            Item item1 = HelperFunctions.getItemFromName(o);
            Item item2 = HelperFunctions.getItemFromName(oo);

            if (!((AdventureManager.getPlayer().hasItem(o) || (AdventureManager.getCurrentRoom().hasItem(o) && !item1.canPickUp())) &&
                    ((AdventureManager.getPlayer().hasItem(oo) || (AdventureManager.getCurrentRoom().hasItem(oo) && !item2.canPickUp()))))) {
                System.out.println("You don't have that item!");
                return;
            }

            AdventureManager.getCurrentAdventure().useFetcher().get(new ItemPair(item1, item2)).run();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information provided!");
        }
    }

    private static void takeCommand(List<String> wordList) {
        if (wordList.size() <= 1) {
            System.out.println("Please specify the item to take!");
            return;
        }

        String o;

        o = HelperFunctions.parseItemName(wordList);

        if (!AdventureManager.getCurrentRoom().hasItem(o)) {
            System.out.println("Can't find that item!");
            return;
        }

        if (!AdventureManager.getCurrentRoom().getItem(o).canPickUp()) {
            System.out.println("You can't take that item!");
            return;
        }

        AdventureManager.getPlayer().addItem(AdventureManager.getCurrentRoom().getItem(o));
        AdventureManager.getCurrentRoom().removeItem(HelperFunctions.getItemFromName(o));
        System.out.println("You took the " + AdventureManager.getPlayer().getItem(o).getName() + ".");

    }

    private static void dropCommand(List<String> wordList) {
        if (wordList.size() <= 1) {
            System.out.println("What item would you like to drop?");
            return;
        }

        String o;

        o = HelperFunctions.parseItemName(wordList);

        if (!AdventureManager.getPlayer().hasItem(o)) {
            System.out.println("You don't have that item!");
        }

        if (AdventureManager.getPlayer().isEquip(HelperFunctions.getItemFromName(o))) {
            AdventureManager.getPlayer().removeEquip();
        }

        AdventureManager.getCurrentRoom().addItem(AdventureManager.getPlayer().getItem(o));
        AdventureManager.getPlayer().removeItem(o);
        System.out.println("You dropped the " + AdventureManager.getCurrentRoom().getItem(o).getName() + ".");

    }

    private static void getDirection(List<String> input) {
        boolean roomChanged = false;
        String intendedDir = input.get(0);
        String n;

        for (Direction direction : Direction.values()) {
            if (!intendedDir.equalsIgnoreCase(direction.getString()) && !intendedDir.equalsIgnoreCase(direction.getFirst())) continue;
            if (AdventureManager.getCurrentRoom().getDirection(direction).isEmpty()) {
                System.out.println("You can't go " + direction.getString() + " here!");
                return;
            }
            roomChanged = true;
            AdventureManager.setCurrentRoom(AdventureManager.getCurrentRoom().getDirection(direction));
            n = HelperFunctions.startsWithVowel(AdventureManager.getCurrentRoom().getName()) ? "n " : " ";
            System.out.println("You find yourself in a" + n + AdventureManager.getCurrentRoom().getName());
        }
        if (!AdventureManager.getCurrentRoom().getEnemies().isEmpty() && roomChanged) {
            new Combat();
        }
    }
    
}
