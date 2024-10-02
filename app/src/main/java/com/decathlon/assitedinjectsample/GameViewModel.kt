package com.decathlon.assitedinjectsample
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {

    var gameEngine: GameEngine? = null

    val gameEngineMessage = mutableStateOf("")

    fun initGameEngine(level: Int) {
        gameEngine = GameEngine(level)
    }

    fun startGame() {
        gameEngineMessage.value = gameEngine?.startGame() ?: "Game engine not initialized"
    }
}