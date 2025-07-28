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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.PlayerScoreCache
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.View.Component.PlayerChip
import com.example.gamemixandroid.View.Component.DynamicPlayerTable
import com.example.gamemixandroid.View.Component.ScoreModal
import com.example.gamemixandroid.ViewModel.GameViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Secondary
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun GameScreen(playerList: List<Player>, viewModel: GameViewModel, navController: NavController) {
    val game by viewModel.gameState.collectAsState()
    print("Current Players: ${game.players}")
    game.players = playerList as MutableList<Player>
    print("Current Players: ${game.players}")
    var showModal by remember { mutableStateOf(false) }
    var selectedPlayer by remember { mutableStateOf<Player?>(null) }
    var context = LocalContext.current
    var gameBegin by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
            .testTag("GameScreen"), // Unique tag for testing
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Section with Logo and Help Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent),
                contentDescription = "GameMix Logo",
                modifier = Modifier.size(50.dp)
            )
            Button(
                onClick = { /* TODO: Help Action */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text("?")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Game Table Section
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(500.dp)
                .background(Color.DarkGray, shape = MaterialTheme.shapes.large)
                .border(
                    width = 6.dp,
                    color = Secondary,
                    shape = MaterialTheme.shapes.large
                ),
            contentAlignment = Alignment.Center,
        ) {
            // Table dynamique des joueurs
            DynamicPlayerTable(
                game.players,
                onPlayerClick = { player ->
                    selectedPlayer = player
                    showModal = true
                }
            )
        }
        if (showModal && selectedPlayer != null) {
            ScoreModal(
                player = selectedPlayer!!,
                onDismiss = { showModal = false },
                onUpdateScore = { newScore ->
                    viewModel.updateScore(selectedPlayer!!.id, newScore)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Action Buttons

        CustomButton(
            text = if (!gameBegin) "Commencer" else "Recommencer",
            textColor = Color.White,
            onClick = {
                viewModel.startGame()
                gameBegin = true
                showModal = false
                selectedPlayer = null},
            height = 60,
            width = 0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "Mélanger",
            textColor = Color.White,
            onClick = { println("Mélange les cartes du Jeu") },
            height = 60,
            width=0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary)
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "Piocher !",
            textColor = Color.White,
            onClick = { println("Pioche une carte du Jeu") },
            height = 60,
            width=0.8f,
            fontSize = 20.sp,
            backgroundColor = Primary)
    }
}
