package game.adventure.gameobjects

class Enemy(
    var name: String,
    var description: String,
    var health: Int,
    var damage: Int,
    private var drops: MutableList<Item>
) {
    fun getDrops(): MutableList<Item> {
        return drops
    }

    fun setDrops(drops: MutableList<Item>) {
        this.drops = drops
    }

    fun addDrop(drop: Item) {
        drops.add(drop)
    }

    fun removeDrop(drop: Item) {
        drops.remove(drop)
    }
}