package com.example.gamemixandroid

import com.example.gamemixandroid.Model.Game
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddPlayerTest {
    private lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** je rejoins la partie avec un pseudonyme,
// **ALORS** je suis ajouté à la partie et mon pseudonyme est visible dans la liste des joueurs.

    @Test
    fun `ajout d'un joueur avec un pseudonyme valide`() {
        val result = game.addPlayer("Alicia")
        assertTrue(result.isSuccess)
        assertTrue(game.players.any { it.name == "Alicia" })
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme déjà pris,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est déjà utilisé.

    @Test
    fun `ajout d'un joueur avec un pseudonyme déjà pris`() {
        game.addPlayer("Alicia")
        val result = game.addPlayer("Alicia")
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme est déjà utilisé.")
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie sans pseudonyme,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est obligatoire.

    @Test
    fun `ajout d'un joueur sans pseudonyme`() {
        val result = game.addPlayer("")
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme est obligatoire.")
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme de plus de 20 caractères,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est trop long.

    @Test
    fun `ajout d'un joueur avec un pseudonyme trop long`() {
        val result = game.addPlayer("A".repeat(21))
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme est trop long.")
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme de moins de 2 caractères,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme est trop court.

    @Test
    fun `ajout d'un joueur avec un pseudonyme trop court`() {
        val result = game.addPlayer("A")
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme est trop court.")
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie avec un pseudonyme contenant des caractères spéciaux,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir de caractères spéciaux.

    @Test
    fun `ajout d'un joueur avec un pseudonyme contenant des caractères spéciaux`() {
        val result = game.addPlayer("Alicia@123")
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme ne doit pas contenir de caractères spéciaux.")
    }

// **ÉTANT DONNÉ** une partie en création,
// **LORSQUE** j'essaie de rejoindre la partie,
// **ALORS** je reçois un message d'erreur m'informant que le pseudonyme ne doit pas contenir d'espaces.

    @Test
    fun `ajout d'un joueur avec un pseudonyme contenant des espaces`() {
        val result = game.addPlayer("Alicia Smith")
        assertTrue(!result.isSuccess)
        assertTrue(result.message == "Le pseudonyme ne doit pas contenir d'espaces.")
    }
}