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
        if (name.isNotBlank() && players.size < maxPlayers) {
            players.add(Player(name = name))
        }
    }

    // Function to remove a player
    fun removePlayer(player: Player) {
        players.remove(player)
    }
}
