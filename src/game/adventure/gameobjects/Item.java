package game.adventure.gameobjects;

public class Item extends GameObject {
    private boolean canPickUp;
    private int damage;

    public boolean isEquippable() {
        return equippable;
    }

    public void setEquippable(boolean equippable) {
        this.equippable = equippable;
    }

    private boolean equippable;

    public Item(Builder builder) {
        super(builder);
        this.canPickUp = builder.canPickUp;
        this.damage = builder.damage;
        this.equippable = builder.equippable;
    }

    public boolean isName(String name) {
        return getName().equalsIgnoreCase(name);
    }

    public boolean canPickUp() {
        return canPickUp;
    }

    public void setCanPickUp(boolean canPickUp) {
        this.canPickUp = canPickUp;
    }

    public boolean getEquippable() {
        return damage != -1;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public static class Builder extends GameObject.Builder<Builder> {
        private boolean canPickUp = true;
        private int damage = 0;
        private boolean equippable = false;

        public Builder canPickUp(boolean canPickUp) {
            this.canPickUp = canPickUp;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder equippable(boolean equippable) {
            this.equippable = equippable;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Item build() {
            return new Item(this);
        }
    }
}
