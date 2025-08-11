
package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ui.theme.Secondary
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.layout.sizeIn

@Composable
fun DynamicPlayerTable(
    players: List<Player>,
    onPlayerClick: (Player) -> Unit
) {
    println("Current Players inside Dynamic Players Table: $players")
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(500.dp)
            .background(Color.DarkGray, shape = MaterialTheme.shapes.large)
            .border(
                width = 6.dp,
                color = Secondary,
                shape = MaterialTheme.shapes.large
            )
            .semantics { contentDescription = "Tableau des joueurs" },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            players.chunked(2).forEach { rowPlayers ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowPlayers.forEach { player ->
                        PlayerChip(
                            name = player.name,
                            modifier = Modifier
                                .sizeIn(minWidth = 48.dp, minHeight = 48.dp)
                                .clickable { onPlayerClick(player) }
                                .testTag("PlayerChip_${player.name}")
                                .semantics { contentDescription = "Bouton : sélectionner ${player.name}" }
                        )
                    }
                }
            }
        }
    }
}
