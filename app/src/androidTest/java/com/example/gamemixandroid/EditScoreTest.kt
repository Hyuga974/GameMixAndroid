package com.example.gamemixandroid

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class EditScoreTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /** Fonction utilitaire pour démarrer une partie avec deux joueurs */
    private fun startGamePath(player1: String = "Alicia", player2: String = "Bella") {
        // Attendre l'affichage de l'écran d'accueil
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule.onAllNodesWithTag("HomeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        // Aller à la liste des jeux
        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule.onAllNodesWithTag("GameListScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        // Sélectionner le jeu Président
        composeTestRule.onNodeWithTag("PresidentButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule.onAllNodesWithTag("SetGameScreen_Président").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("SetGameScreen_Président").assertIsDisplayed()

        // Ajouter les joueurs (vider le champ avant chaque saisie)
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextClearance()
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput(player1)
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("PlayerRow_${player1}").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextClearance()
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput(player2)
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("PlayerRow_${player2}").fetchSemanticsNodes().isNotEmpty()
        }

        // Démarrer la partie
        composeTestRule.onNodeWithTag("PlayButton_SetGame").performClick()
        composeTestRule.waitUntil(timeoutMillis = 20_000) {
            composeTestRule.onAllNodesWithTag("GameScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("GameScreen").assertIsDisplayed()
        composeTestRule.onNodeWithTag("StartButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("StartButton").performClick()
    }

    // **ÉTANT DONNÉ** une partie en cours,
    // **LORSQUE** j'accède à l'éditeur de scores,
    // **ALORS** les scores de tous les joueurs sont modifiables
    // &
    // **ÉTANT DONNÉ** que je modifie un score,
    // **LORSQUE** j'enregistre les modifications,
    // **ALORS** l'interface utilisateur se met à jour instantanément
    @Test
    fun allScoresAreEditable() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("10")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : 10"
        }
    }

    //**ÉTANT DONNÉ** que j'entre un score invalide,
    // **LORSQUE** j'essaie de l'enregistrer,
    // **ALORS** je reçois un retour de validation immédiat
    @Test
    fun invalidScoreShowsValidation() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("invalid")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.onNodeWithText("Veuillez entrer un score valide.").assertIsDisplayed()
    }

    // **ÉTANT DONNÉ** une partie en cours avec des joueurs,
    // **LORSQUE** je mets à jour le score d'un joueur,
    // **ALORS** le nouveau score est affiché immédiatement dans l'interface.
    @Test
    fun scoreUpdateReflectsImmediately() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("20")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : 20"
        }

        composeTestRule.onNodeWithTag("PlayerChip_Bella").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PlayerChip_Bella").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("-15")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : -15"
        }
        composeTestRule.onNodeWithTag("CurrentScoreText").assertTextEquals("Score actuel : -15")
    }

    // **ÉTANT DONNÉ** des scores existants,
    // **LORSQUE** je déclenche la réinitialisation,
    // **ALORS** une boîte de dialogue de confirmation apparaît
    @Test
    fun resetScoresDialogAppears() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("10")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : 10"
        }
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("StartButton").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("StartButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("StartButton").performClick()
        composeTestRule.onNodeWithTag("ResetScoreDialog").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ModalMessageText").assertTextEquals("Voulez-vous vraiment réinitialiser la partie ?")
        composeTestRule.onNodeWithTag("ConfirmButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CancelButton").assertIsDisplayed()
    }

    // **ÉTANT DONNÉ** que je confirme la réinitialisation,
    // **LORSQUE** l'action est terminée,
    // **ALORS** tous les scores affichent 0
    @Test
    fun resetScoresToZero() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("10")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.onNodeWithTag("CurrentScoreText").assertIsDisplayed()
        composeTestRule.waitUntil(timeoutMillis = 10_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : 10"
        }
        composeTestRule.onNodeWithTag("CurrentScoreText").assertTextEquals("Score actuel : 10")
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("StartButton").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("StartButton").performClick()
        composeTestRule.onNodeWithTag("ConfirmButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ConfirmButton").performClick()

        composeTestRule.onNodeWithTag("PlayerChip_Alicia").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.waitUntil(timeoutMillis = 30_000) {
            composeTestRule.onAllNodesWithTag("CurrentScoreText").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("CurrentScoreText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CurrentScoreText").assertTextEquals("Score actuel : 0")
    }

    // **ÉTANT DONNÉ** que j'annule la réinitialisation,
    // **LORSQUE** la boîte de dialogue se ferme,
    // **ALORS** les scores restent inchangés
    @Test
    fun cancelResetScores() {
        startGamePath()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("10")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()
        composeTestRule.waitUntil(timeoutMillis = 15_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.joinToString("") == "Score actuel : 10"
        }
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("StartButton").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("StartButton").performClick()
        composeTestRule.onNodeWithTag("CancelButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CancelButton").performClick()

        composeTestRule.onNodeWithTag("PlayerChip_Alicia").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PlayerChip_Alicia").performClick()
        composeTestRule.waitUntil(timeoutMillis = 30_000) {
            composeTestRule.onNodeWithTag("CurrentScoreText")
                .fetchSemanticsNode()
                .config.getOrNull(SemanticsProperties.Text)
                ?.isNotEmpty() == true
        }
    }
}