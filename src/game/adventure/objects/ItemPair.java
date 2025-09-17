package game.adventure.objects;

import game.adventure.gameobjects.Item;

import java.util.Objects;

public record ItemPair(Item item1, Item item2) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPair other)) return false;
        return (Objects.equals(item1, other.item1) && Objects.equals(item2, other.item2)) ||
                (Objects.equals(item1, other.item2) && Objects.equals(item2, other.item1));
    }

}
