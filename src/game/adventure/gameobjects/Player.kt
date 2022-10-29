package game.adventure.gameobjects

class Player(var health: Int, private val playerInventory: MutableList<Item>, equip: Int) {
    private var equip = 0

    /** equip should be an index in the players inventory. if the number is out of bounds, the player will automatically have no equip. -1 == no equip  */
    init {
        if (equip + 1 > playerInventory.size || equip + 1 < 0 || playerInventory.isEmpty()) {
            this.equip = -1
        } else {
            this.equip = equip
        }
    }

    fun isEquip(item: String): Boolean {
        val index = getInt(item)
        return if (index == -1) {
            false
        } else index == equip
    }

    @Throws(IndexOutOfBoundsException::class)
    fun setEquip(item: String) {
        val index = getInt(item)
        if (index == -1) {
            throw IndexOutOfBoundsException()
        }
        setEquip(index)
    }

    fun removeEquip() {
        setEquip(-1)
    }

    fun addItem(newItem: Item) {
        playerInventory.add(newItem)
    }

    private fun getInt(item: String): Int {
        for (i in playerInventory.indices) {
            if (playerInventory[i].name.equals(item, ignoreCase = true)) {
                return i
            }
        }
        return -1
    }

    val damage: Int
        get() = if (equip == -1) -1 else getItem(equip).damage
    val equipItem: Item
        get() = getItem(equip)

    fun getItem(i: Int): Item {
        return playerInventory[i]
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getItem(item: String): Item {
        val i = getInt(item)
        if (i == -1) {
            throw IndexOutOfBoundsException()
        }
        return playerInventory[i]
    }

    fun hasItem(item: String): Boolean {
        return getInt(item) != -1
    }

    fun removeItem(oldItem: Item) {
        playerInventory.remove(oldItem)
    }

    @Throws(IndexOutOfBoundsException::class)
    fun removeItem(oldItem: String) {
        val index = getInt(oldItem)
        if (index == -1) {
            throw IndexOutOfBoundsException()
        }
        removeItem(getItem(index))
    }

    fun clearInventory() {
        playerInventory.clear()
    }

    val inventory: List<Item>
        get() = playerInventory

    fun setEquip(equip: Int) {
        this.equip = equip
    }

    fun getEquip(): Int {
        return equip
    }
}