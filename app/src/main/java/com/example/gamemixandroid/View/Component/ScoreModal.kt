package com.example.gamemixandroid.View.Component
// PlayerScoreModal.kt
import android.widget.Toast
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
import com.example.gamemixandroid.PlayerScoreCache
import com.example.gamemixandroid.ui.theme.Background

@Composable
fun ScoreModal(
    player: Player,
    onDismiss: () -> Unit,
    onUpdateScore: (Int) -> Unit,
) {
    var scoreInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = MaterialTheme.shapes.medium, color = Background, modifier = Modifier.testTag("ScoreModal")) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Joueur : ${player.name}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.testTag("PlayerNameText"))
                Text("Score actuel : ${player.score}",
                    modifier = Modifier.testTag("CurrentScoreText"))
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                OutlinedTextField(
                    value = scoreInput,
                    onValueChange = {
                        scoreInput = it
                        errorMessage = null // Efface l’erreur à la saisie
                    },
                        label = { Text("Nouveau score") },
                        modifier = Modifier.testTag("ScoreInputField"),
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomButton(
                    text = "Valider",
                    onClick = {
                        val newScore = scoreInput.toIntOrNull()
                        if (newScore != null) {
                            onUpdateScore(newScore)
                            scoreInput = ""
                            errorMessage = null
                            onDismiss()
                        } else {
                            errorMessage = "Veuillez entrer un score valide."
                        }
                    },
                    modif = Modifier.testTag("UpdateScoreButton"))
            }
        }
    }
}