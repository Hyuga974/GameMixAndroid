package com.example.gamemixandroid

import com.example.gamemixandroid.Model.Game
import org.junit.Before
import org.junit.Test
import kotlin.jvm.Throws

class EditScoreTest {
    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

// **ÉTANT DONNÉ** une partie en cours,
// **LORSQUE** je mets à jour le score d'un joueur,
// **ALORS** le score du joueur est mis à jour dans la liste des joueurs.

    @Test
    @Throws(Exception::class)
    fun `update player score in game`() {
        game.addPlayer("Alice")
        game.addPlayer("Bob")
        game.startGame()
        val player = game.players.firstOrNull { it.name == "Alice" }
        if (player != null) {
            var result = game.updateScore(player.id, 10)
            assert(result.isSuccess)
            assert(player.score == 10)

            result = game.updateScore(player.id, 20)
            assert(result.isSuccess)
            assert(player.score == 30)
        } else {
            throw Exception("Player not found")
        }
    }

// **ÉTANT DONNÉ** une partie en cours,
// **LORSQUE** je mets à jour le score d'un joueur avec un score négatif,
// **ALORS** le score du joueur est mis à jour dans la liste des joueurs.

    @Test
    @Throws(Exception::class)
    fun `update player score with negative value`() {
        game.addPlayer("Alice")
        game.addPlayer("Bob")
        game.startGame()
        val player = game.players.firstOrNull { it.name == "Alice" }
        if (player != null) {
            var result = game.updateScore(player.id, -5)
            assert(result.isSuccess)
            assert(player.score == -5)

            result = game.updateScore(player.id, -10)
            assert(result.isSuccess)
            assert(player.score == -15)
        } else {
            throw Exception("Player not found")
        }
    }

// **ÉTANT DONNÉ** une partie en cours,
// **LORSQUE** j'essaie de mettre à jour le score d'un joueur qui n'existe pas,
// **ALORS** je reçois un message d'erreur m'informant que le joueur n'existe pas.

    @Test
    fun `update score of non-existent player`() {
        game.addPlayer("Alice")
        game.addPlayer("Bob")
        game.startGame()
        val result = game.updateScore(java.util.UUID.randomUUID(), 10)
        assert(!result.isSuccess)
        assert(result.message == "Le joueur n'existe pas.")
    }
}