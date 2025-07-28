package com.example.gamemixandroid

import com.example.gamemixandroid.Model.Game
import org.junit.Before
import org.junit.Test

class CreateGameTest {

    private lateinit var game: Game

    @Before
    fun setUp() {
        game = Game(minPlayers = 2, maxPlayers = 4)
    }

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je crée une partie,
// **ALORS** la partie est créée et je suis redirigé vers l'écran de jeu.

//    @Test
//    fun `creation of a part`() {
//        val result = game.startGame()
//        assert(result.isSuccess)
//        assert(game.players.isEmpty())
//    }

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je crée une partie avec trop peu de participants,
// **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est insuffisant pour lancer la partie.

    @Test
    fun `create game with too few players`() {
        game.addPlayer("Alicia")
        val result = game.startGame()
        assert(!result.isSuccess)
        assert(result.message == "Nombre de joueurs insuffisant pour démarrer la partie.")
    }

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je crée une partie avec trop de participants,
// **ALORS** je reçois un message d'erreur m'informant que le nombre de joueurs est trop élevé pour lancer la partie.

    @Test
    fun `creating a game with too many players`() {
        game.addPlayer("Alicia")
        game.addPlayer("Bella")
        game.addPlayer("Charlie")
        game.addPlayer("David")
        val result = game.addPlayer("Eve")
        assert(!result.isSuccess)
        assert(result.message == "La limite de joueurs est atteinte.")
    }

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je crée une partie,
// **ALORS** je reçois bien autant de joueurs que j'en ai demandé.

    @Test
    fun `creation of a game with the requested number of players`() {
        game.addPlayer("Alicia")
        game.addPlayer("Bella")
        val result = game.startGame()
        assert(result.isSuccess)
        assert(game.players.size == 2)
        assert(game.players[0].name == "Alicia")
        assert(game.players[1].name == "Bella")
    }

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je crée une partie,
// **ALORS** tous les joueurs commencent avec un score de 0.

    @Test
    fun `creating a game with an initial score of 0 for all players`() {
        game.addPlayer("Alicia")
        game.addPlayer("Bella")
        val result = game.startGame()
        assert(result.isSuccess)
        assert(game.players.all { it.score == 0 })
    }
}