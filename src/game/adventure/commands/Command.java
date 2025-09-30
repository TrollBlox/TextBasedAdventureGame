package game.adventure.commands;

public interface Command<T> {
    void execute(T arg);

    String getName();

    String getHelpMessage();
}
