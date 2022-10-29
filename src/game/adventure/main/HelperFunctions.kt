package game.adventure.main

import game.adventure.gameobjects.Room
import game.adventure.utils.Constants
import java.util.*

object HelperFunctions {
    fun parseItemName(wordList: List<String?>): String {
        val builder = StringBuilder()
        for (i in 1 until wordList.size) {
            builder.append(wordList[i])
            builder.append(" ")
        }
        return builder.toString().trim { it <= ' ' }
    }

    fun lookNorth() {
        if (GameLogic.currentRoom.n == -1) return
        if (getMap(GameLogic.currentRoom.n).hasVisited) {
            println("To the north: " + getMap(GameLogic.currentRoom.n).name)
            return
        }
        println("There is something to the north.")
    }

    fun lookSouth() {
        if (GameLogic.currentRoom.s == -1) return
        if (getMap(GameLogic.currentRoom.s).hasVisited) {
            println("To the south: " + getMap(GameLogic.currentRoom.s).name)
            return
        }
        println("There is something to the south.")
    }

    fun lookEast() {
        if (GameLogic.currentRoom.e == -1) return
        if (getMap(GameLogic.currentRoom.e).hasVisited) {
            println("To the east: " + getMap(GameLogic.currentRoom.e).name)
            return
        }
        println("There is something to the east.")
    }

    fun lookWest() {
        if (GameLogic.currentRoom.w == -1) return
        if (getMap(GameLogic.currentRoom.w).hasVisited) {
            println("To the west: " + getMap(GameLogic.currentRoom.w).name)
            return
        }
        println("There is something to the west.")
    }

    fun lookCardinal() {
        lookNorth()
        lookSouth()
        lookEast()
        lookWest()
    }

    fun firstLetterUpper(string: String): String {
        return string.substring(0, 1).uppercase(Locale.getDefault()) + string.substring(1)
    }

    fun getMap(i: Int): Room {
        return Constants.map[i]!!
    }

    fun setMap(newRoom: Room?, i: Int) {
        Constants.map[i] = newRoom
    }

    fun startsWithVowel(string: String): Boolean {
        return when (string.lowercase(Locale.getDefault())[0]) {
            'a', 'e', 'i', 'o', 'u', 'y' -> true
            else -> false
        }
    }
}