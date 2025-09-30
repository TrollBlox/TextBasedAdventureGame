package game.adventure.commands;

public class Help implements Command<Void> {
    @Override
    public void execute(Void arg) {
        for (Commands command : Commands.values()) {
            System.out.println(command.getName() + " - " + command.getHelpMessage());
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelpMessage() {
        return "Display this help screen";
    }
}
