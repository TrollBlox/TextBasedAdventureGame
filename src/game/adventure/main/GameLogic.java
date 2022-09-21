package game.adventure.main;

import game.adventure.gameobjects.Enemy;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Room;
import game.adventure.utils.Constants;
import game.adventure.utils.GameInit;
import game.adventure.utils.Utils;
import java.util.*;

public class GameLogic {
    private static final Scanner s = new Scanner(System.in);

    public static final GameInit gi = new GameInit();

    public static Room currentRoom = Utils.getMap(0);

    public void takeInput() {
        String input;
        System.out.println("Press h for help!");

        String n = Utils.startsWithVowel(currentRoom.getName()) ? "n " : " ";

        System.out.println("You find yourself in a" + n + currentRoom.getName());
        do {
            currentRoom.setHasVisited(true);
            System.out.print("> ");
            input = s.nextLine().toLowerCase();
            ParseCommand(input);
        } while (!input.equals("q") && !input.equals("quit"));
    }

    public void ParseCommand(String input) {
        List<String> wordList;
        wordList = wordList(input);

        try {
            wordList.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You must enter a command!");
            return;
        }

        try {
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
                case "a":
                case "attack":
                    attackCommand();
                    break;
                case "q":
                case "quit":
                    break;
                default:
                    System.out.println("I don't understand " + wordList.get(0));
                    break;
            }
        } finally {
            if (wordList.size() != 0) {
                switch (wordList.get(0)) {
                    case "l":
                    case "look":
                    case "n":
                    case "north":
                    case "s":
                    case "south":
                    case "e":
                    case "east":
                    case "w":
                    case "west":
                        break;
                    default:
                        enemyAttack();
                }
            }
        }
    }

    public void playerDeath(String killer) {
        System.out.println("The " + killer + " killed you!");
        System.out.println("rip bozo");
        System.exit(0);
    }

    public void enemyAttack() {
        for (Enemy e : currentRoom.getEnemies()) {
            Constants.player.setHealth(Constants.player.getHealth() - e.getDamage());
            if (Constants.player.getHealth() <= 0) {
                playerDeath(e.getName());
                return;
            }
            System.out.println("The " + e.getName() + " did " + e.getDamage() + " damage to you!");
        }
    }

    public void attackCommand() {
        List<Enemy> toRemove = new ArrayList<>();

        if (currentRoom.getEnemies().size() == 0) {
            System.out.println("There are no enemies in this room!");
            return;
        }

        for (Enemy e : currentRoom.getEnemies()) {
            int damage = Constants.player.getEquip() == -1 ? 1 : Constants.player.getDamage();

            e.setHealth(e.getHealth() - damage);
            System.out.println("You did " + damage + " damage to the " + e.getName());

            if (e.getHealth() <= 0) {
                System.out.println("You killed the " + e.getName());
                toRemove.add(e);
                System.out.print("It dropped: ");
                for (int i = 0; i < e.getDrops().size(); i++) {
                    Item drop = e.getDrops().get(i);
                    currentRoom.addItem(drop);
                    String separator = i == e.getDrops().size() - 1 ? "." : ", ";

                    System.out.println(e.getDrops().get(i).getName() + separator);
                }
            }
        }

        try {
            currentRoom.getEnemies().removeAll(toRemove);
        } catch (NullPointerException ignore) {
        }
    }

    public void equipCommand(List<String> wordList) {
        String o = Utils.firstLetterUpper(HelperFunctions.parseItemName(wordList));

        if (!Constants.player.hasItem(o)) {
            System.out.println("You don't have the item " + o + "!");
            return;
        }

        Constants.player.setEquip(o);
        System.out.println("Equipped " + o);
    }

    public void inspectItem(List<String> wordList) {
        String o = Utils.firstLetterUpper(HelperFunctions.parseItemName(wordList));

        for (Item i : currentRoom.getItems()) {
            if (i.getName().equals(o)) {
                System.out.println(i.getName() + ": " + i.getDescription());
                return;
            }
        }
        for (Item i : Constants.player.getInventory()) {
            if (i.getName().equals(o)) {
                System.out.println(i.getName() + ": " + i.getDescription());
                return;
            }
        }
        for (Enemy e : currentRoom.getEnemies()) {
            if (e.getName().equals(o)) {
                System.out.println(e.getName() + " (" + e.getHealth() + " hp " + e.getDamage() + " damage): " + e.getDescription());
                return;
            }
        }
        System.out.println("I don't know what " + o + " is!");
    }

    public void helpCommand() {
        System.out.println("""
                Type "q" to quit, "l" to look around or at an item, "i" to view your inventory, "t" to take items,
                "d" to drop items, "a" to attack, "eq" to equip items, and "u" to use items on each other!
                Type "n", "s", "e", and "w" to move!""");
    }

    public void lookItemsCommand() {
        if (currentRoom.getItems().isEmpty()) {
            System.out.println("There are no items in this room!");
            return;
        }

        System.out.print("Items near you: ");
        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            String separator = i == currentRoom.getItems().size() - 1 ? "." : ", ";

            System.out.print(currentRoom.getItem(i).getName() + separator);
        }
        System.out.println();

    }

    public void lookCommand(List<String> wordList) {
        if (wordList.size() > 1) {
            inspectItem(wordList);
            return;
        }

        System.out.println(currentRoom.getName() + ": " + currentRoom.getDescription());

        lookItemsCommand();

        HelperFunctions.lookCardinal();

        if (currentRoom.getEnemies().isEmpty()) return;

        for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
            Enemy e = currentRoom.getEnemies().get(i);
            System.out.println("Enemies near you: ");
            System.out.println(e.getName() + ": " + e.getDescription());
        }
    }

    public void getInventory() {
        if (Constants.player.getInventory().isEmpty() || (Constants.player.getInventory().size() == 1 && Constants.player.getEquip() != -1)) {
            System.out.println("You have nothing in your inventory!");
            return;
        }

        System.out.println("Your inventory: ");
        for (int i = 0; i < Constants.player.getInventory().size(); i++) {
            if (i == Constants.player.getEquip()) {
                continue;
            }

            String nameSuffix = Constants.player.getDamage() == -1 ? ": " : " (" + Constants.player.getDamage() + " damage): ";

            System.out.println(Constants.player.getItem(i).getName() + nameSuffix + Constants.player.getItem(i).getDescription());
        }
    }

    public void inventoryCommand() {
        System.out.println("Stats:");
        System.out.println("Health: " + Constants.player.getHealth());
        int damage = Constants.player.getEquip() == -1 ? 1 : Constants.player.getDamage();
        System.out.println("Damage: " + damage);
        System.out.println();

        getInventory();

        if (Constants.player.getEquip() != -1) {
            System.out.println("\nEquipped item: \n" + Constants.player.getEquipItem().getName() + " (" + Constants.player.getDamage() + " damage): " + Constants.player.getEquipItem().getDescription());
        }
    }

    public List<String> wordList(String input) {
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

    public void useCommand(List<String> wordList) {
        String o, oo;

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

            o = Utils.firstLetterUpper(builder.toString().trim());

            oo = Utils.firstLetterUpper(HelperFunctions.parseItemName(wordList));

            if (!((Constants.player.hasItem(o) || currentRoom.hasItem(o)) && ((Constants.player.hasItem(oo) || currentRoom.hasItem(oo))))) {
                System.out.println("You don't have that item!");
                return;
            }

            Utils.useFetcher(o, oo).run();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information provided!");
        }
    }

    public void takeCommand(List<String> wordList) {
        String o;

        o = Utils.firstLetterUpper(HelperFunctions.parseItemName(wordList));

        if (!currentRoom.hasItem(o)) {
            System.out.println("Can't find that item!");
            return;
        }

        if (!currentRoom.getItem(o).canPickUp()) {
            System.out.println("You can't take that item!");
            return;
        }

        Constants.player.addItem(currentRoom.getItem(o));
        currentRoom.removeItem(o);
        System.out.println("You took the " + o + ".");

    }

    public void dropCommand(List<String> wordList) {
        String o;

        o = Utils.firstLetterUpper(HelperFunctions.parseItemName(wordList));

        if (!Constants.player.hasItem(o)) {
            System.out.println("You don't have that item!");
        }

        if (Constants.player.isEquip(o)) {
            Constants.player.setEquip(-1);
        }

        currentRoom.addItem(Constants.player.getItem(o));
        Constants.player.removeItem(o);
        System.out.println("You dropped the " + o + ".");

    }

    public void getDirection(List<String> input) {
        boolean roomChanged = false;
        String direction = input.get(0);
        String n;

        try {
            switch (direction) {
                case "n", "north" -> {
                    if (currentRoom.getN() == -1) {
                        System.out.println("You can't go north here!");
                        return;
                    }
                    roomChanged = true;
                    currentRoom = Utils.getMap(currentRoom.getN());
                    n = Utils.startsWithVowel(currentRoom.getName()) ? "n " : " ";
                    System.out.println("You find yourself in a" + n + currentRoom.getName());
                }
                case "s", "south" -> {
                    if (currentRoom.getS() == -1) {
                        System.out.println("You can't go south here!");
                        return;
                    }
                    roomChanged = true;
                    currentRoom = Utils.getMap(currentRoom.getS());
                    n = Utils.startsWithVowel(currentRoom.getName()) ? "n " : " ";
                    System.out.println("You find yourself in a" + n + currentRoom.getName());
                }
                case "e", "east" -> {
                    if (currentRoom.getE() == -1) {
                        System.out.println("You can't go east here!");
                        return;
                    }
                    roomChanged = true;
                    currentRoom = Utils.getMap(currentRoom.getE());
                    n = Utils.startsWithVowel(currentRoom.getName()) ? "n " : " ";
                    System.out.println("You find yourself in a" + n + currentRoom.getName());
                }
                case "w", "west" -> {
                    if (currentRoom.getW() == -1) {
                        System.out.println("You can't go west here!");
                        return;
                    }
                    roomChanged = true;
                    currentRoom = Utils.getMap(currentRoom.getW());
                    n = Utils.startsWithVowel(currentRoom.getName()) ? "n " : " ";
                    System.out.println("You find yourself in a" + n + currentRoom.getName());
                }
            }
        } finally {
            if (currentRoom.getEnemies().isEmpty() || !roomChanged) {
                return;
            }
            for (int i = 0; i < currentRoom.getEnemies().size(); i++) {
                Enemy e = currentRoom.getEnemies().get(i);
                System.out.print("Enemies near you: ");
                String separator = i == currentRoom.getEnemies().size() - 1 ? "." : ", ";
                System.out.println(e.getName() + " (" + e.getHealth() + " hp " + e.getDamage() + " damage)" + separator);
            }
        }
    }
}
