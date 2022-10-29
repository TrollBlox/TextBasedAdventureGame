package game.adventure.main

import game.adventure.gameobjects.Enemy
import game.adventure.main.HelperFunctions.getMap
import game.adventure.main.HelperFunctions.lookCardinal
import game.adventure.main.HelperFunctions.parseItemName
import game.adventure.main.HelperFunctions.startsWithVowel
import game.adventure.utils.Constants
import game.adventure.utils.GameInit
import game.adventure.utils.Utils.useFetcher
import java.util.*
import kotlin.system.exitProcess

class GameLogic {
    fun takeInput() {
        var input: String
        println("Press h for help!")
        val n = if (startsWithVowel(currentRoom.name)) "n " else " "
        println("You find yourself in a" + n + currentRoom.name)
        do {
            currentRoom.hasVisited = true
            print("> ")
            input = s.nextLine().lowercase(Locale.getDefault())
            ParseCommand(input)
        } while (input != "q" && input != "quit")
    }

    fun ParseCommand(input: String?) {
        val wordList: MutableList<String?>
        wordList = wordList(input)
        try {
            wordList[0]
        } catch (e: IndexOutOfBoundsException) {
            println("You must enter a command!")
            return
        }
        try {
            when (wordList[0]) {
                "n", "north", "s", "south", "e", "east", "w", "west" -> getDirection(wordList)
                "i", "inv", "inventory" -> inventoryCommand()
                "h", "help" -> helpCommand()
                "l", "look" -> lookCommand(wordList)
                "t", "take" -> takeCommand(wordList)
                "d", "drop" -> dropCommand(wordList)
                "u", "use" -> useCommand(wordList)
                "eq", "equip" -> equipCommand(wordList)
                "a", "attack" -> attackCommand()
                "q", "quit" -> {}
                else -> println("I don't understand " + wordList[0])
            }
        } finally {
            if (wordList.size != 0) {
                when (wordList[0]) {
                    "l", "look", "n", "north", "s", "south", "e", "east", "w", "west" -> {}
                    else -> enemyAttack()
                }
            }
        }
    }

    private fun playerDeath(killer: String) {
        println("The $killer killed you!")
        println("rip bozo")
        exitProcess(0)
    }

    private fun enemyAttack() {
        for (e in currentRoom.getEnemies()) {
            Constants.player.health = Constants.player.health - e.damage
            if (Constants.player.health <= 0) {
                playerDeath(e.name)
                return
            }
            println("The " + e.name + " did " + e.damage + " damage to you!")
        }
    }

    private fun attackCommand() {
        val toRemove: MutableList<Enemy> = ArrayList()
        if (currentRoom.getEnemies().isEmpty()) {
            println("There are no enemies in this room!")
            return
        }
        for (e in currentRoom.getEnemies()) {
            val damage = if (Constants.player.getEquip() == -1) 1 else Constants.player.damage
            e.health = e.health - damage
            println("You did " + damage + " damage to the " + e.name)
            if (e.health <= 0) {
                println("You killed the " + e.name)
                toRemove.add(e)
                print("It dropped: ")
                for (i in e.getDrops().indices) {
                    val drop = e.getDrops()[i]
                    currentRoom.addItem(drop)
                    val separator = if (i == e.getDrops().size - 1) "." else ", "
                    println(e.getDrops()[i].name + separator)
                }
            }
        }
        try {
            currentRoom.getEnemies().removeAll(toRemove)
        } catch (ignore: NullPointerException) {
        }
    }

    private fun equipCommand(wordList: List<String?>) {
        if (wordList.size == 1) {
            Constants.player.removeEquip()
            println("Removed your equip")
            return
        }
        val o = parseItemName(wordList)
        if (!Constants.player.hasItem(o)) {
            println("You don't have the item $o!")
            return
        }
        Constants.player.setEquip(o)
        println("Equipped $o")
    }

    private fun inspectItem(wordList: List<String?>?) {
        val o = parseItemName(wordList!!)
        for (i in currentRoom.getItems()) {
            if (i.name.equals(o, ignoreCase = true)) {
                println(i.name + ": " + i.description)
                return
            }
        }
        for (i in Constants.player.inventory) {
            if (i.name.equals(o, ignoreCase = true)) {
                println(i.name + ": " + i.description)
                return
            }
        }
        for (e in currentRoom.getEnemies()) {
            if (e.name.equals(o, ignoreCase = true)) {
                println(e.name + " (" + e.health + " hp " + e.damage + " damage): " + e.description)
                return
            }
        }
        println("I don't know what $o is!")
    }

    private fun helpCommand() {
        println(/* !!! Hit visitElement for element type: class org.jetbrains.kotlin.nj2k.tree.JKErrorExpression !!! */)
    }

    private fun lookItemsCommand() {
        if (currentRoom.getItems().isEmpty()) {
            println("There are no items in this room!")
            return
        }
        print("Items near you: ")
        for (i in currentRoom.getItems().indices) {
            val separator = if (i == currentRoom.getItems().size - 1) "." else ", "
            print(currentRoom.getItem(i).name + separator)
        }
        println()
    }

    private fun lookCommand(wordList: List<String?>) {
        if (wordList.size > 1) {
            inspectItem(wordList)
            return
        }
        println(currentRoom.name + ": " + currentRoom.description)
        lookItemsCommand()
        lookCardinal()
        if (currentRoom.getEnemies().isEmpty()) return
        for (i in currentRoom.getEnemies().indices) {
            val e = currentRoom.getEnemies()[i]
            println("Enemies near you: ")
            println(e.name + ": " + e.description)
        }
    }

    private val inventory: Unit
        get() {
            if (Constants.player.inventory.isEmpty() || Constants.player.inventory.size == 1 && Constants.player.getEquip() != -1) {
                println("You have nothing in your inventory!")
                return
            }
            println("Your inventory: ")
            for (i in Constants.player.inventory.indices) {
                if (i == Constants.player.getEquip()) {
                    continue
                }
                val nameSuffix =
                    if (Constants.player.getItem(i).damage == -1) ": " else " (" + Constants.player.getItem(i).damage + " damage): "
                println(Constants.player.getItem(i).name + nameSuffix + Constants.player.getItem(i).description)
            }
        }

    private fun inventoryCommand() {
        println("Health: " + Constants.player.health)
        val damage = if (Constants.player.getEquip() == -1) 1 else Constants.player.damage
        println("Damage: $damage")
        println()
        inventory
        if (Constants.player.getEquip() != -1) {
            println(
                """
Equipped item: 
${Constants.player.equipItem.name} (${Constants.player.damage} damage): ${Constants.player.equipItem.description}"""
            )
        }
    }

    private fun wordList(input: String?): MutableList<String?> {
        val delims = " \t,.:;?!\"'"
        val strList: MutableList<String?> = ArrayList()
        val tokenizer = StringTokenizer(input, delims)
        var t: String?
        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken()
            strList.add(t)
        }
        return strList
    }

    private fun useCommand(wordList: MutableList<String?>) {
        val o: String
        val oo: String
        try {
            val builder = StringBuilder()
            var i = 1
            while (i < wordList.size) {
                if (wordList[i] == "on") {
                    wordList.removeAt(i)
                    break
                }
                builder.append(wordList[i])
                builder.append(" ")
                wordList.removeAt(i)
                i--
                i++
            }
            o = builder.toString().trim { it <= ' ' }
            oo = parseItemName(wordList)
            if (!((Constants.player.hasItem(o) || currentRoom.hasItem(o)) && (Constants.player.hasItem(oo) || currentRoom.hasItem(
                    oo
                )))
            ) {
                println("You don't have that item!")
                return
            }
            useFetcher(o, oo).run()
        } catch (e: IndexOutOfBoundsException) {
            println("Not enough information provided!")
        }
    }

    private fun takeCommand(wordList: List<String?>) {
        if (wordList.size <= 1) {
            println("What item would you like to take?")
            return
        }
        val o: String = parseItemName(wordList)
        if (!currentRoom.hasItem(o)) {
            println("Can't find that item!")
            return
        }
        if (!currentRoom.getItem(o).canPickUp()) {
            println("You can't take that item!")
            return
        }
        Constants.player.addItem(currentRoom.getItem(o))
        currentRoom.removeItem(o)
        println("You took the " + Constants.player.getItem(o).name + ".")
    }

    private fun dropCommand(wordList: List<String?>) {
        if (wordList.size <= 1) {
            println("What item would you like to drop?")
            return
        }
        val o: String = parseItemName(wordList)
        if (!Constants.player.hasItem(o)) {
            println("You don't have that item!")
        }
        if (Constants.player.isEquip(o)) {
            Constants.player.setEquip(-1)
        }
        currentRoom.addItem(Constants.player.getItem(o))
        Constants.player.removeItem(o)
        println("You dropped the " + currentRoom.getItem(o).name + ".")
    }

    private fun getDirection(input: List<String?>) {
        var roomChanged = false
        val direction = input[0]
        val n: String
        try {
            when (direction) {
                "n", "north" -> {
                    if (currentRoom.n == -1) {
                        println("You can't go north here!")
                        return
                    }
                    roomChanged = true
                    currentRoom = getMap(currentRoom.n)
                    n = if (startsWithVowel(currentRoom.name)) "n " else " "
                    println("You find yourself in a" + n + currentRoom.name)
                }

                "s", "south" -> {
                    if (currentRoom.s == -1) {
                        println("You can't go south here!")
                        return
                    }
                    roomChanged = true
                    currentRoom = getMap(currentRoom.s)
                    n = if (startsWithVowel(currentRoom.name)) "n " else " "
                    println("You find yourself in a" + n + currentRoom.name)
                }

                "e", "east" -> {
                    if (currentRoom.e == -1) {
                        println("You can't go east here!")
                        return
                    }
                    roomChanged = true
                    currentRoom = getMap(currentRoom.e)
                    n = if (startsWithVowel(currentRoom.name)) "n " else " "
                    println("You find yourself in a" + n + currentRoom.name)
                }

                "w", "west" -> {
                    if (currentRoom.w == -1) {
                        println("You can't go west here!")
                        return
                    }
                    roomChanged = true
                    currentRoom = getMap(currentRoom.w)
                    n = if (startsWithVowel(currentRoom.name)) "n " else " "
                    println("You find yourself in a" + n + currentRoom.name)
                }
            }
        } finally {
            if (currentRoom.getEnemies().isEmpty() || !roomChanged) return
            for (i in currentRoom.getEnemies().indices) {
                val e = currentRoom.getEnemies()[i]
                print("Enemies near you: ")
                val separator = if (i == currentRoom.getEnemies().size - 1) "." else ", "
                println(e.name + " (" + e.health + " hp " + e.damage + " damage)" + separator)
            }
        }
    }

    companion object {
        private val s = Scanner(System.`in`)
        val gi = GameInit()
        var currentRoom = getMap(0)
    }
}