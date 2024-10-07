package com.decathlon.assitedinjectsample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val level = savedStateHandle.get<Int>("level") ?: 0

    // We can create the gameEngine directly during the viewmodel creation
    var gameEngine = GameEngine(level)

    val gameEngineMessage = mutableStateOf("")

    fun startGame() {
        // No risk of NPE here
        gameEngineMessage.value = gameEngine.startGame()
    }
}
