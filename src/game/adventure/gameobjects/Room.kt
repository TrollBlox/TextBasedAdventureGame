package game.adventure.gameobjects

class Room(
    var name: String,
    var description: String,
    var n: Int,
    var s: Int,
    var e: Int,
    var w: Int,
    private val items: MutableList<Item>,
    private var enemies: MutableList<Enemy>
) {
    var hasVisited = false
    fun getItem(index: Int): Item {
        return items[index]
    }

    fun getItems(): List<Item> {
        return items
    }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun removeItem(item: String) {
        val index = getInt(item)
        if (index == -1) {
            throw IndexOutOfBoundsException()
        }
        items.remove(getItem(index))
    }

    fun clearItems() {
        items.clear()
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    private fun getInt(name: String): Int {
        for (i in items.indices) {
            if (items[i].name.equals(name, ignoreCase = true)) {
                return i
            }
        }
        return -1
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getItem(item: String): Item {
        val i = getInt(item)
        if (i == -1) {
            throw IndexOutOfBoundsException()
        }
        return getItems()[i]
    }

    fun hasItem(item: String): Boolean {
        return getInt(item) != -1
    }

    fun getEnemies(): MutableList<Enemy> {
        return enemies
    }

    fun setEnemies(enemies: MutableList<Enemy>) {
        this.enemies = enemies
    }

    fun addEnemy(enemy: Enemy) {
        enemies.add(enemy)
    }

    fun removeEnemy(enemy: Enemy) {
        enemies.remove(enemy)
    }
}