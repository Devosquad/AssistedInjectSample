package com.decathlon.assitedinjectsample
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel(assistedFactory = GameViewModel.GameViewModelFactory::class)
class GameViewModel @AssistedInject constructor(

    @Assisted val level: Int): ViewModel() {

    @AssistedFactory
    interface GameViewModelFactory {
        fun create(level: Int): GameViewModel
    }

    // We can create the gameEngine directly during the viewmodel creation
    var gameEngine = GameEngine(level)

    val gameEngineMessage = mutableStateOf("")

    fun startGame() {
        // No risk of NPE here
        gameEngineMessage.value = gameEngine.startGame()
    }
}