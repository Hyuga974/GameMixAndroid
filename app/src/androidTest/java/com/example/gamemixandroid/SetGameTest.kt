package com.example.gamemixandroid

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SetGameTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

// **ÉTANT DONNÉ** que je suis sur l'écran de création de partie,
// **LORSQUE** je sélectionne un type de jeu (par exemple, Belote),
// **ALORS** les options du jeu apparaissent
    @Test
    fun gameOptionsAppearWhenGameSelected() {
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("BeloteButton").performClick()
        composeTestRule.onNodeWithTag("SetGameScreen_Belote").assertIsDisplayed()
    }


// **ÉTANT DONNÉ** que j'ai ajouté des joueurs,
// **LORSQUE** j'atteins le nombre minimum de joueurs requis,
// **ALORS** le bouton JOUER devient activé
    @Test
    fun playButtonEnabledWhenMinPlayersReached() {
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("BeloteButton").performClick()
        composeTestRule.onNodeWithTag("SetGameScreen_Belote").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton_SetGame").assertIsNotEnabled()
        // Ajouter des joueurs jusqu'à atteindre le minimum requis
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Alicia")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        composeTestRule.onNodeWithTag("PlayButton_SetGame").assertIsDisplayed()
        composeTestRule.onNodeWithTag("PlayButton_SetGame").assertIsNotEnabled()

        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Bella")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        // Vérifier que le bouton JOUER est activé
        composeTestRule.onNodeWithTag("PlayButton_SetGame").assertIsDisplayed()
    }

// **ÉTANT DONNÉ** que j'ai configuré la partie,
// **LORSQUE** je clique sur JOUER,
// **ALORS** la partie démarre et je suis redirigé vers l'écran de jeu
    @Test
    fun gameStartsWhenPlayButtonClicked() {
        composeTestRule.onNodeWithTag("HomeScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("PlayButton").performClick()
        composeTestRule.onNodeWithTag("GameListScreen").assertIsDisplayed()

        composeTestRule.onNodeWithTag("BeloteButton").performClick()
        composeTestRule.onNodeWithTag("SetGameScreen_Belote").assertIsDisplayed()

        // Ajouter des joueurs
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Alicia")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Bella")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Costa")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()
        composeTestRule.onNodeWithTag("TextField_AddPlayer").performTextInput("Diana")
        composeTestRule.onNodeWithTag("Button_AddPlayer").performClick()

        // Démarrer la partie
        composeTestRule.onNodeWithTag("PlayButton_SetGame").performClick()
        composeTestRule.onNodeWithTag("GameScreen").assertIsDisplayed()
    }

}