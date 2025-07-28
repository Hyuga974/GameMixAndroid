package com.example.gamemixandroid

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditScoreTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

// **ÉTANT DONNÉ** une partie en cours,
// **LORSQUE** j'accède à l'éditeur de scores,
// **ALORS** les scores de tous les joueurs sont modifiables
    // &
// **ÉTANT DONNÉ** que je modifie un score,
// **LORSQUE** j'enregistre les modifications,
// **ALORS** l'interface utilisateur se met à jour instantanément
    @Test
    fun allScoresAreEditable() {
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PresidentButton").performClick()
        composeTestRule.onNodeWithTag("SetGameScreen_Président").assertIsDisplayed()

        // Ajouter Alice
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Alice")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        // Ajouter Bob
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Bob")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        // Vérifier que les joueurs sont affichés
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("PlayerRow_Alice").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            composeTestRule.onAllNodesWithTag("PlayerRow_Bob").fetchSemanticsNodes().isNotEmpty()
        }

        // Démarrer la partie
        composeTestRule.onNodeWithTag("PlayButton_SetGame").performClick()
        composeTestRule.onNodeWithTag("GameScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayerChip_Alice").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()

        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("10")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()

        composeTestRule.onNodeWithTag("PlayerChip_Alice").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CurrentScoreText").assertTextEquals("Score actuel : 10")





    }

//**ÉTANT DONNÉ** que j'entre un score invalide,
// **LORSQUE** j'essaie de l'enregistrer,
// **ALORS** je reçois un retour de validation immédiat
    @Test
    fun invalidScoreShowsValidation() {
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PresidentButton").performClick()
        composeTestRule.onNodeWithTag("SetGameScreen_Président").assertIsDisplayed()

        // Ajouter Alice
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Alice")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        // Ajouter Bob
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Bob")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        // Démarrer la partie
        composeTestRule.onNodeWithTag("PlayButton_SetGame").performClick()
        composeTestRule.onNodeWithTag("GameScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayerChip_Alice").performClick()
        composeTestRule.onNodeWithTag("ScoreModal").assertIsDisplayed()

        // Entrer un score invalide
        composeTestRule.onNodeWithTag("ScoreInputField").performTextInput("invalid")
        composeTestRule.onNodeWithTag("UpdateScoreButton").performClick()

        // Vérifier le message d'erreur
        composeTestRule.onNodeWithText("Veuillez entrer un score valide.").assertIsDisplayed()
    }
}