package com.example.gamemixandroid

import com.example.gamemixandroid.Model.Game
import org.junit.Before
import org.junit.Test

class RemovePlayerTest {
    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game()
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** je quitte la partie,
// **ALORS** je ne suis plus dans la liste des joueurs.

    @Test
    fun `removing a player from the game`() {
        game.addPlayer("Alice")
        game.addPlayer("Bob")
        val result = game.removePlayer("Alice")
        assert(result.isSuccess)
        assert(game.players.none { it.name == "Alice" })
        assert(game.players.any { it.name == "Bob" })

    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** je quitte la partie,
// **ALORS** je reçois un message de confirmation de mon départ.

    @Test
    fun `deletion of a player with confirmation message`() {
        game.addPlayer("Alice")
        game.addPlayer("Bob")
        val result = game.removePlayer("Alice")
        assert(result.isSuccess)
        assert(result.message == "Joueur supprimé.")
    }

}