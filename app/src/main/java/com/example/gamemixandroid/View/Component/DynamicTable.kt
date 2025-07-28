
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PlayerChip(
                        name = players[0].name,
                        modifier = Modifier
                            .clickable { onPlayerClick(players[0]) }
                            .testTag("PlayerChip_${players[0].name}")

                    )
                    if (playerCount >= 2) PlayerChip(
                        name = players[1].name,
                        modifier = Modifier
                            .clickable { onPlayerClick(players[1]) }
                            .testTag("PlayerChip_${players[1].name}")
                    )
                }
            }

            if (playerCount >= 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PlayerChip(
                        name = players[2].name,
                        modifier = Modifier
                            .clickable { onPlayerClick(players[2]) }
                            .testTag("PlayerChip_${players[2].name}")
                    )
                    if (playerCount >= 4) PlayerChip(
                        name = players[3].name,
                        modifier = Modifier
                            .clickable { onPlayerClick(players[3]) }
                            .testTag("PlayerChip_${players[3].name}")
                    )
                }
            }

            if (playerCount >= 5) {
                PlayerChip(
                    name = players[4].name,
                    modifier = Modifier
                        .clickable { onPlayerClick(players[4]) }
                        .testTag("PlayerChip_${players[4].name}")
                )
            }

            if (playerCount > 5) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    players.drop(5).forEach { player ->
                        PlayerChip(
                            name = player.name,
                            modifier = Modifier
                                .clickable { onPlayerClick(player) }
                                .testTag("PlayerChip_${player.name}")                     )
                    }
                }
            }
        }
    }
}