package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ui.theme.Secondary

@Composable
fun DynamicPlayerTable(players: List<Player>) {
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
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val playerCount = players.size

            if (playerCount >= 1) {
                // Top row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PlayerChip(players[0].name)
                    if (playerCount >= 2) PlayerChip(players[1].name)
                }
            }

            if (playerCount >= 3) {
                // Middle row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PlayerChip(players[2].name)
                    if (playerCount >= 4) PlayerChip(players[3].name)
                }
            }

            if (playerCount >= 5) {
                // Bottom center
                PlayerChip(players[4].name)
            }

            // Additional players from index 5+
            if (playerCount > 5) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    players.drop(5).forEach { player ->
                        PlayerChip(player.name)
                    }
                }
            }
        }
    }
}
