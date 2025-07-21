package com.example.gamemixandroid.ViewModel


import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamemixandroid.Model.GameState
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.PlayerScoreCache
import kotlinx.coroutines.flow.Flow
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

    fun editScoreToPlayer(playerID : UUID, newScore: Int, context: Context) {
        viewModelScope.launch {
            PlayerScoreCache.saveScore(context, playerID.toString(), newScore)
//            val updatedPlayers = _gameState.value.players.map { player ->
//                if (player.id == playerID ){
//                    player.copy(score = player.score + newScore)
//                }
//                else player
//            }
//            _gameState.emit(_gameState.value.copy(players = updatedPlayers))
        }
    }

    fun getPlayerScore(context: Context, playerId: String): Flow<Int> {
        return PlayerScoreCache.getScore(context, playerId)
    }

    fun startGame(context: Context) {
        viewModelScope.launch {
            val initialPlayers = _gameState.value.players.map { player ->
                PlayerScoreCache.saveScore(context, player.id.toString(), 0)
                player.copy(score = 0)
            }
            _gameState.emit(_gameState.value.copy(players = initialPlayers))
        }
    }
}