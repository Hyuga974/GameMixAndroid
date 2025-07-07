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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.View.Component.PlayerChip
import com.example.gamemixandroid.ViewModel.GameViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Secondary

@Composable
fun GameScreen(viewModel: GameViewModel, navController: NavController, players: List<Player>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp),
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
            // Players Positioned Around the Table
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Top Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    PlayerChip("P1") // Top Left
                    PlayerChip("P3") // Top Right
                }

                // Middle Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    PlayerChip("P2") // Middle Left
                    PlayerChip("P4") // Middle Right
                }

                // Bottom Center
                PlayerChip("P5")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        // Action Buttons
        CustomButton(
            "Commencer",
            textColor = Color.White,
            onClick = { println("Commnce le Jeu") },
            height = 60,
            width=0.8f,
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


@Composable
fun ActionButton(text: String) {
    Button(
        onClick = { /* TODO: Implement Actions */ },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 4.dp),
        colors = ButtonDefaults.buttonColors(Primary)
    ) {
        Text(text, fontSize = 18.sp, color = Color.White)
    }
}
