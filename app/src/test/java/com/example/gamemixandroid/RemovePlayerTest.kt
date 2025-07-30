package com.example.gamemixandroid

import com.example.gamemixandroid.Model.Game
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RemovePlayerTest {
    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** je quitte la partie,
// **ALORS** je ne suis plus dans la liste des joueurs.

    @Test
    fun `removing a player from the game`() {
        game.addPlayer("Alicia")
        game.addPlayer("Bella")
        val result = game.removePlayer("Alicia")
        assert(result.isSuccess)
        assert(game.players.none { it.name == "Alicia" })
        assert(game.players.any { it.name == "Bella" })

    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** je quitte la partie,
// **ALORS** je reçois un message de confirmation de mon départ.

    @Test
    fun `deletion of a player with confirmation message`() {
        game.addPlayer("Alicia")
        game.addPlayer("Bella")
        val result = game.removePlayer("Alicia")
        assert(result.isSuccess)
        assert(result.message == "Joueur supprimé.")
    }

}