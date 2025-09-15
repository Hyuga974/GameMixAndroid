package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ui.theme.Background
import kotlinx.coroutines.delay
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun ModalScore(
    player: Player,
    onDismiss: () -> Unit,
    onUpdateScore: (Int) -> Unit,
) {
    var scoreInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var shouldClose by remember { mutableStateOf(false) }

    LaunchedEffect(shouldClose) {
        if (shouldClose) {
            delay(1000)
            onDismiss()
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Background,
            modifier = Modifier
                .testTag("ScoreModal")
                .semantics { contentDescription = "Fenêtre de modification du score" }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Joueur : ${player.name}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .testTag("PlayerNameText")
                        .semantics { contentDescription = "Nom du joueur : ${player.name}" }
                )
                Text(
                    "Score actuel : ${player.score}",
                    modifier = Modifier
                        .testTag("CurrentScoreText")
                        .semantics { contentDescription = "Score actuel : ${player.score}" }
                )
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .semantics { contentDescription = "Erreur : $errorMessage" }
                    )
                }
                OutlinedTextField(
                    value = scoreInput,
                    onValueChange = {
                        scoreInput = it
                        errorMessage = null
                    },
                    label = { Text("Nouveau score") },
                    modifier = Modifier
                        .testTag("ScoreInputField")
                        .semantics { contentDescription = "Champ de saisie pour entrer le nouveau score" },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomButton(
                    text = "Valider",
                    onClick = {
                        val newScore = scoreInput.toIntOrNull()
                        if (newScore != null) {
                            player.score += newScore
                            onUpdateScore(player.score)
                            scoreInput = ""
                            errorMessage = null
                            shouldClose = true
                        } else {
                            errorMessage = "Veuillez entrer un score valide."
                        }
                    },
                    modif = Modifier
                        .testTag("UpdateScoreButton")
                        .semantics { contentDescription = "Bouton : valider le nouveau score" }
                )
            }
        }
    }
}
