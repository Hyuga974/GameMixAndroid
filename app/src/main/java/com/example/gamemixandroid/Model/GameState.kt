package com.example.gamemixandroid.Model

data class GameState (
    val players: List<Player>,
    val currentAction: String,
    val gameName : String
)