package com.example.gamemixandroid.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamemixandroid.Model.GameState
import com.example.gamemixandroid.Model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    // État initial du jeu
    private val _gameState = MutableStateFlow(
        GameState(
            players = emptyList(),
            currentAction = "",
            gameName = ""
        )
    )


    // Exposer l'état du jeu à la View
    val gameState: StateFlow<GameState> = _gameState

    fun setupGame(players: List<Player>, initialAction: String, gameName: String) {
        viewModelScope.launch {
            _gameState.emit(
                GameState(
                    players = players,
                    currentAction = initialAction,
                    gameName = gameName
                )
            )
        }
    }

    fun updateAction(newAction: String) {
        viewModelScope.launch {
            _gameState.emit(_gameState.value.copy(currentAction = newAction))
        }
    }
}