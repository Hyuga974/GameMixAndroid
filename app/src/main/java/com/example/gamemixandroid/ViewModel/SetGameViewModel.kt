package com.example.gamemixandroid.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gamemixandroid.Model.Player

class SetGameViewModel : ViewModel() {
    private var playerIdCounter = 0  // Ensures unique player IDs

    // State: List of players
    val players = mutableStateListOf<Player>()

    // Function to add a player
    fun addPlayer(name: String, maxPlayers: Int) {
        if (players.size < maxPlayers  && isValidPseudo(name)) {
            players.add(Player(name = name))
        }
    }

    // Function to remove a player
    fun removePlayer(player: Player) {
        players.remove(player)
    }
    fun isValidPseudo(pseudo: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9_-]{3,15}$")
        return regex.matches(pseudo)
    }
}
