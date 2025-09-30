package game.adventure.main;

import game.adventure.commands.Commands;
import game.adventure.gameobjects.Item;
import game.adventure.gameobjects.Room;
import game.adventure.util.Adventure;
import java.util.List;
import java.util.Scanner;

public class Logic {

    private final Adventure currentAdventure;
    private Room currentRoom;
    private final Scanner s = new Scanner(System.in);
    private Commands currentCommand;

    public Logic(Adventure currentAdventure) {
        this.currentAdventure = currentAdventure;
    }

    public void runAdventure() {
        currentAdventure.init();
        currentAdventure.getStartingRoom().setHasVisited(true);
        currentRoom = currentAdventure.getStartingRoom();
        takeInput();
    }

    private void takeInput() {
        List<String> input;
        while (true) {
            System.out.print("> ");
            input = List.of(s.nextLine().split(" "));
            if (input.isEmpty()) {
                System.out.println("Please enter a command!");
                continue;
            }
            for (Commands command : Commands.values()) {
                if (command.getName().equalsIgnoreCase(input.get(0))) {
                    currentCommand = command;
                    input.remove(0);
                    parseArguments(input);
                }
            }
            System.out.println("I don't understand " + input.get(0));
        }
    }

    private void parseArguments(List<String> args) {
        Class<?> argType = currentCommand.getArgType();
        if (argType.equals(Void.class)) {
            currentCommand.execute(null);
            return;
        }
        if (args.isEmpty()) {
            System.out.println("Not enough information provided!");
            return;
        }
        if (argType.equals(Item.class)) {
            for (Item item : currentAdventure.getItems()) {
                if (item.getName().equalsIgnoreCase(args.get(0))) {
                    currentCommand.execute(item);
                    return;
                }
            }
            System.out.println("Item not found!");
            return;
        }
        if (args.size() < 3)
    }
}
