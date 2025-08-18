package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Secondary

@Composable
fun PlayerTable(players: List<Player>, onRemove: (Player) -> Unit) {
    Card(
        shape = RoundedCornerShape(7),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.5f)
            .semantics { contentDescription = "Liste des joueurs" }
            .background(Color.Transparent)
            .semantics { contentDescription = "Liste des joueurs inscrits pour la partie" }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NoName)
        ) {
            if (players.isEmpty()){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .semantics { contentDescription = "Aucun joueur saisi" }
                ) {
                    Text(
                        text = "Aucun joueur n'a été saisi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                players.forEachIndexed { index, player ->
                    PlayerRow(player, onRemove, hasButton = true)
                    if (index < players.size -1) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.Black.copy(alpha = 0.3f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PlayerRow(player: Player, removePlayer: (Player) -> Unit, hasButton: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .testTag("PlayerRow_${player.name}")
            .semantics { contentDescription = "Joueur ${player.name}" },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = player.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        if (hasButton) {
            Button(
                onClick = { removePlayer(player) },
                modifier = Modifier
                    .size(48.dp)
                    .semantics { contentDescription = "Supprimer joueur ${player.name}" },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    Secondary
                )
            ) {
                Text("-", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
