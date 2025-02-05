package com.example.gamemixandroid.Model

import java.util.UUID

data class Player (
    val id: UUID = UUID.randomUUID(),
    val name : String,
    val score: Int = 0
)