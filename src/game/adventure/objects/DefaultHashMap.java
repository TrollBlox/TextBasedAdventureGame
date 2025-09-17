package game.adventure.objects;

import java.util.HashMap;

public class DefaultHashMap<K, V> extends HashMap<K, V> {
    private V defaultValue;

    @Override
    public V get(Object key) {
        if (this.containsKey(key)) return super.get(key);
        return defaultValue;
    }

    public DefaultHashMap(V defaultValue) {
        this.defaultValue = defaultValue;
    }

    public V getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(V defaultValue) {
        this.defaultValue = defaultValue;
    }

}
