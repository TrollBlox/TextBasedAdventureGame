package game.adventure.gameobjects

class Item(
    var name: String, var description: String, private var canPickUp: Boolean,
    /** a damage value of -1 would make the item non-equippable  */
    var damage: Int
) {

    fun isName(name: String): Boolean {
        return this.name == name
    }

    fun canPickUp(): Boolean {
        return canPickUp
    }

    fun setCanPickUp(canPickUp: Boolean) {
        this.canPickUp = canPickUp
    }

    val equippable: Boolean
        get() = damage != -1
}