package com.example.gamemixandroid.Model

import java.util.UUID


data class GameResult(val isSuccess: Boolean, val message: String? = null)

enum class GameStateEnum { CREATED, STARTED, FINISHED }

class Game(
    private val minPlayers: Int = 2,
    private val maxPlayers: Int = 8
) {
    var players = mutableListOf<Player>()
    var state: GameStateEnum = GameStateEnum.CREATED

    fun addPlayer(name: String): GameResult {
        if (state != GameStateEnum.CREATED) return GameResult(false, "Impossible d'ajouter un joueur après le début de la partie.")
        if (name.isBlank()) return GameResult(false, "Le pseudonyme est obligatoire.")
        if (name.length > 20) return GameResult(false, "Le pseudonyme est trop long.")
        if (name.length < 2) return GameResult(false, "Le pseudonyme est trop court.")
        if (name.contains(" ")) return GameResult(false, "Le pseudonyme ne doit pas contenir d'espaces.")
        if (!name.matches(Regex("^[A-Za-z0-9]+$"))) return GameResult(false, "Le pseudonyme ne doit pas contenir de caractères spéciaux.")
        if (players.any { it.name.equals(name, ignoreCase = true) }) return GameResult(false, "Le pseudonyme est déjà utilisé.")
        if (players.size >= maxPlayers) return GameResult(false, "La limite de joueurs est atteinte.")
        players.add(Player(name = name))
        return GameResult(true)
    }

    fun removePlayer(name: String): GameResult {
        val removed = players.removeIf { it.name == name }
        return if (removed) GameResult(true, "Joueur supprimé.") else GameResult(false, "Joueur introuvable.")
    }

    fun startGame(): GameResult {
        if (players.size < minPlayers) return GameResult(false, "Nombre de joueurs insuffisant pour démarrer la partie.")
        state = GameStateEnum.STARTED
        players.forEach { it.score = 0 }
        return GameResult(true)
    }

    fun updateScore(id: UUID, newScore: Int): GameResult {
        val player = players.find { it.id == id } ?: return GameResult(false, "Le joueur n'existe pas.")
        player.score = newScore
        return GameResult(true)
    }

    fun resetScores(): GameResult {
        players.forEach { it.score = 0 }
        return GameResult(true)
    }
}