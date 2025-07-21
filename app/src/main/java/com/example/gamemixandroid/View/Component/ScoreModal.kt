package com.example.gamemixandroid.View.Component
// PlayerScoreModal.kt
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.PlayerScoreCache
import com.example.gamemixandroid.ui.theme.Background

@Composable
fun ScoreModal(
    player: Player,
    onDismiss: () -> Unit,
    onUpdateScore: (Int) -> Unit
) {
    var scoreInput by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(shape = MaterialTheme.shapes.medium, color = Background) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Joueur : ${player.name}", style = MaterialTheme.typography.titleLarge)
                Text("Score actuel : ${player.score}")
                OutlinedTextField(
                    value = scoreInput,
                    onValueChange = { scoreInput = it },
                    label = { Text("Nouveau score") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomButton(text = "Valider", onClick = {
                    val newScore = scoreInput.toIntOrNull()
                    if (newScore != null) {
                        onUpdateScore(newScore)
                        scoreInput = ""
                        onDismiss()
                    }
                })
            }
        }
    }
}