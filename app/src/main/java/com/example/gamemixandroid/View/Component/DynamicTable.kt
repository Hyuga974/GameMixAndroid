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
            .size(300.dp)
            .background(Color.DarkGray, shape = MaterialTheme.shapes.large)
            .border(6.dp, Secondary, MaterialTheme.shapes.large),
        contentAlignment = Alignment.Center
    ) {
        // Centre de la table
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
        )

        // Top players (0, 1)
        if (players.size > 0) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (players.size > 0) PlayerChip(players[0].name)
                if (players.size > 1) PlayerChip(players[1].name)
            }
        }

        // Bottom players (6, 7)
        if (players.size > 6) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (players.size > 6) PlayerChip(players[6].name)
                if (players.size > 7) PlayerChip(players[7].name)
            }
        }

        // Left players (2, 3)
        if (players.size > 2) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (players.size > 2) PlayerChip(players[2].name)
                if (players.size > 3) PlayerChip(players[3].name)
            }
        }

        // Right players (4, 5)
        if (players.size > 4) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (players.size > 4) PlayerChip(players[4].name)
                if (players.size > 5) PlayerChip(players[5].name)
            }
        }

        // Center players (8, 9)
        if (players.size > 8) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (players.size > 8) PlayerChip(players[8].name)
                if (players.size > 9) PlayerChip(players[9].name)
            }
        }
    }
}
