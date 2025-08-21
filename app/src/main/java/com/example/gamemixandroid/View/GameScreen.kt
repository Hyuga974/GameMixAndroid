package com.example.gamemixandroid.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.View.Component.DynamicPlayerTable
import com.example.gamemixandroid.View.Component.ModalCheck
import com.example.gamemixandroid.View.Component.ModalScore
import com.example.gamemixandroid.ViewModel.GameViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Secondary
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import com.example.gamemixandroid.telemetry.Telemetry

@Composable
fun GameScreen(playerList: List<Player>, viewModel: GameViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        Telemetry.setScreen("GameScreen")
    }

    val game by viewModel.gameState.collectAsState()
    game.players = playerList as MutableList<Player>

    var showModal by remember { mutableStateOf(false) }
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }
    var gameBegin by remember { mutableStateOf(false) }
    var showResetModal by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
            .testTag("GameScreen")
            .semantics { contentDescription = "Écran de jeu GameMix" },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo + bouton aide
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent),
                contentDescription = "Logo de l'application GameMix",
                modifier = Modifier.size(50.dp)
            )
            Button(
                onClick = { /* TODO: Help Action */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.Gray),
                modifier = Modifier.semantics { contentDescription = "Bouton d'aide - afficher les règles du jeu" }
            ) {
                Text("?", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Table de jeu
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(500.dp)
                .background(Color.DarkGray, shape = MaterialTheme.shapes.large)
                .border(6.dp, Secondary, shape = MaterialTheme.shapes.large)
                .semantics { contentDescription = "Table de jeu avec liste des joueurs" },
            contentAlignment = Alignment.Center
        ) {
            DynamicPlayerTable(
                game.players,
                onPlayerClick = { player ->
                    selectedPlayer = player
                    showModal = true
                }
            )
        }

        if (showModal && selectedPlayer != null) {
            ModalScore(
                player = selectedPlayer!!,
                onDismiss = { showModal = false },
                onUpdateScore = { newScore ->
                    viewModel.updateScore(selectedPlayer!!.id, newScore)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Boutons d’action
        CustomButton(
            text = if (!gameBegin) "Commencer" else "Recommencer",
            textColor = Color.White,
            onClick = {
                if (gameBegin) {
                    showResetModal = true
                } else {
                    viewModel.startGame()
                    gameBegin = true
                    showModal = false
                    selectedPlayer = null
                }
            },
            height = 60,
            width = 0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary,
            modif = Modifier
                .testTag("StartButton")
                .semantics {
                    contentDescription = if (!gameBegin) "Commencer la partie" else "Recommencer la partie"
                }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            text = "Mélanger",
            textColor = Color.White,
            onClick = { println("Mélange les cartes du Jeu") },
            height = 60,
            width=0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary,
            on = false,
            modif = Modifier.semantics { contentDescription = "Mélanger les cartes du jeu" }
        )

        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(
            text = "Piocher !",
            textColor = Color.White,
            onClick = { println("Pioche une carte du Jeu") },
            height = 60,
            width=0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary,
            on = false,
            modif = Modifier.semantics { contentDescription = "Piocher une carte" }
        )
    }

    if (showResetModal) {
        ModalCheck(
            message = "Voulez-vous vraiment réinitialiser la partie ?",
            tagname = "ResetScoreDialog",
            onConfirm = {
                viewModel.resetGame()
                gameBegin = false
                showResetModal = false
            },
            onCancel = { showResetModal = false }
        )
    }
}