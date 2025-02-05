package com.example.gamemixandroid.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.ViewModel.GameViewModel
import com.example.gamemixandroid.ui.theme.*

@Composable
fun SetGameScreen(
    maxPlayers: Int = 10,
    minPlayers: Int = 2,
    gameName: String,
    viewModel: GameViewModel = viewModel()
) {
    var newPlayerName by remember { mutableStateOf(TextFieldValue("")) }
    val players = viewModel.players
    val keyboardController = LocalSoftwareKeyboardController.current // Keyboard controller

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(vertical = 30.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 60.dp)
                .windowInsetsPadding(WindowInsets.ime), // Moves UI up when keyboard appears
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp),
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    // Logo Section
                    Image(
                        painter = painterResource(id = R.drawable.gamemixlogo_transparent),
                        contentDescription = "GameMix Logo",
                        modifier = Modifier.size(150.dp)
                    )

                    // Title Section
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(
                            text = "Bienvenue dans une partie de",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = gameName,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                        Text(
                            text = "Entrez le nom des participants :",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Input Section
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = newPlayerName,
                            onValueChange = { newPlayerName = it },
                            placeholder = { Text("New player ...") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                viewModel.addPlayer(newPlayerName.text, maxPlayers)
                                newPlayerName = TextFieldValue("") // Clear input
                                keyboardController?.hide() // Hide keyboard
                            },
                            modifier = Modifier.size(50.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                Secondary,
                                disabledBackgroundColor = Secondary.copy(alpha = 0.5f)
                            ),
                            enabled = newPlayerName.text.isNotBlank() && players.size < maxPlayers
                        ) {
                            Text("+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            items(players) { player ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .background(Background, shape = MaterialTheme.shapes.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = player.name,
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Button(
                        onClick = { viewModel.removePlayer(player) },
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Primary),
                    ) {
                        Text("-", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Play Button Section
        CustomButton(
            onClick = { /* TODO: Navigate to the game screen */ },
            width = 0.8f,
            height = 60,
            text = "JOUER →",
            fontSize = 24.sp,
            textColor = Color.White,
            backgroundColor = Secondary,
            on = players.size >= minPlayers,
            modif = Modifier
                .align(Alignment.BottomCenter)
                .imePadding() // Keeps button visible when keyboard opens
        )
    }
}
