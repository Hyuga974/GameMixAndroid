package com.example.gamemixandroid.ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.gamemixandroid.Model.Game
import java.util.UUID

class GameViewModel : ViewModel() {
    private val game = Game()
    private val _gameState = MutableStateFlow(game)
    val gameState: StateFlow<Game> = _gameState

    fun addPlayer(name: String) {
        val result = game.addPlayer(name)
        if (result.isSuccess) _gameState.value = game
    }

    fun removePlayer(name: String) {
        val result = game.removePlayer(name)
        if (result.isSuccess) _gameState.value = game
    }

    fun startGame() {
        val result = game.startGame()
        if (result.isSuccess) _gameState.value = game
    }

    fun updateScore(id: UUID, score: Int) {
        val result = game.updateScore(id, score)
        if (result.isSuccess) _gameState.value = game
    }

    fun resetGame() {
        game.resetGame()
        _gameState.value = game
    }
}