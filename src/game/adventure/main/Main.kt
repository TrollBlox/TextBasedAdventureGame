package game.adventure.main

object Main {
    var gl = GameLogic()
    @JvmStatic
    fun main(args: Array<String>) {
        gl.takeInput()
    }
}