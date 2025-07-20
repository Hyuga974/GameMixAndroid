package com.example.gamemixandroid.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamemixandroid.Model.GameState
import com.example.gamemixandroid.Model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

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

    fun editScoreToPlayer(playerID : UUID, newScore: Int) {
        viewModelScope.launch {
            val updatedPlayers = _gameState.value.players.map { player ->
                if (player.id == playerID ) player.copy(score = player.score + newScore)
                else player
            }
            _gameState.emit(_gameState.value.copy(players = updatedPlayers))
        }
    }
}