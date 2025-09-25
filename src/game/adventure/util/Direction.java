package game.adventure.util;

public enum Direction {
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west");

    private final String string;

    Direction(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public String getFirst() {
        return String.valueOf(string.charAt(0));
    }

    public Direction getOpposite() {
        switch (this) {
            case NORTH -> {
                return SOUTH;
            }
            case SOUTH -> {
                return NORTH;
            }
            case EAST -> {
                return WEST;
            }
            case WEST -> {
                return EAST;
            }
        }
        return null;
    }
}
