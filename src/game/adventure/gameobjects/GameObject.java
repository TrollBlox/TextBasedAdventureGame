package game.adventure.gameobjects;

public abstract class GameObject {
    private String name, description;

    protected <T extends Builder<T>> GameObject(Builder<T> builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static abstract class Builder<T extends Builder<T>> {
        private String name = "";
        private String description = "";

        public T name(String name) {
            this.name = name;
            return self();
        }

        public T description(String description) {
            this.description = description;
            return self();
        }

        protected abstract T self();

        public abstract GameObject build();
    }
}
