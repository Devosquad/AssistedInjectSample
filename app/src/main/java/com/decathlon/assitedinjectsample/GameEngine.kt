package com.decathlon.assitedinjectsample

class GameEngine(
    private val level: Int
) {

    fun startGame(): String = "Game started at level $level"
    
}
