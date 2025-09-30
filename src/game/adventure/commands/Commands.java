package game.adventure.commands;

import game.adventure.gameobjects.Item;
import game.adventure.objects.ItemPair;
import game.adventure.util.Direction;

public enum Commands {
    MOVE(new Move(), Direction.class),
    INVENTORY(new Inventory(), Void.class),
    HELP(new Help(), Void.class),
    LOOK(new Look(), Void.class),
    LOOK_AT_ITEM(new LookAtItem(), Item.class),
    TAKE(new Take(), Item.class),
    DROP(new Drop(), Item.class),
    USE(new Use(), ItemPair.class),
    EQUIP(new Equip(), Item.class),
    QUIT(null, Void.class);

    private final Command<?> command;
    private final Class<?> argType;

    <T> Commands(Command<T> command, Class<T> argType) {
        this.command = command;
        this.argType = argType;
    }

    public String getName() {
        return command.getName();
    }

    public String getHelpMessage() {
        return command.getHelpMessage();
    }

    public Class<?> getArgType() {
        return argType;
    }

    @SuppressWarnings("unchecked")
    public <T> void execute(T arg) {
        ((Command<T>) command).execute(arg);
    }
}
