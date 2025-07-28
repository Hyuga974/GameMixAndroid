package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ViewModel.SetGameViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Secondary

@Composable
fun SetPlayer (
    player: Player,
    hasButton : Boolean = false,
    fontSize: TextUnit = 18.sp,
    height: Int = 60,
    width : Float = 0.0f,
    cornerRadius: Int = 20,
    modif : Modifier = Modifier,
    viewModel: SetGameViewModel = viewModel()
) {
    Card(
        shape = RoundedCornerShape(190.dp), // Adjust corner radius as needed
        backgroundColor = Color.Red,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp)
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .background(Background, shape = MaterialTheme.shapes.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = player.name,
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 18.sp,
                color = Color.Black
            )
            if (hasButton) {
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

}

// 🔹 Displays the player list in a table style
@Composable
fun PlayerTable(players: List<Player>, onRemove: (Player) -> Unit) {
    Card(
        shape = RoundedCornerShape(7),
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NoName, shape = MaterialTheme.shapes.medium)
        ) {
            if (players.isEmpty()){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Aucun joueur n'a était saisis",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }else{
                players.forEachIndexed { index, player ->
                    PlayerRow(player, onRemove, hasButton = true)
                    if (index < players.size - 1) { // Only add a separator if it's not the last player
                        Divider(color = Color.Black.copy(alpha = 0.3f), thickness = 1.dp)
                    }
                }
            }
        }
    }

}

// 🔹 Single row with player name and remove button
@Composable
fun PlayerRow(player: Player, removePlayer: (Player) -> Unit, hasButton: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .testTag("PlayerRow_${player.name}"), // Unique test tag for each player row
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
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Secondary) // Ensure proper color usage
            ) {
                Text("-", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold) // Use a defined color
            }

        }
    }
}