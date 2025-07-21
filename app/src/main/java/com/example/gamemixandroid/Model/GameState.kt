package com.example.gamemixandroid.Model

import com.example.gamemixandroid.Model.Player

data class GameState (
    var players: List<Player>,
    val currentAction: String,
    val gameName : String
)