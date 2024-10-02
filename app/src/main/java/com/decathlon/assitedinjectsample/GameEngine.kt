package com.decathlon.assitedinjectsample

class GameEngine(val level: Int) {

    fun startGame(): String {
        return "Game started at level $level"
    }
}
