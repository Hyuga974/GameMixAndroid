package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.ViewModel.SetGameViewModel
import com.example.gamemixandroid.ui.theme.Background
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