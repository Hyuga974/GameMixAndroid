package com.example.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemixandroid.R // Replace with your actual R file
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Tertiary

@Composable
fun GameListScreen(viewModel: HomeViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically), // Set spacing and alignment
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Section
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent),
                contentDescription = "GameMix Logo",
                modifier = Modifier
                    .height(350.dp)
                    .width(350.dp)
            )

            // Buttons Section
            Button(
                onClick = { /* TODO: Navigate to Belote Screen */ },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Primary)
            ) {
                Text(text = "Belote", fontSize = 18.sp, color = Color.White)
            }

            Button(
                onClick = { /* TODO: Navigate to Président Screen */ },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Primary)
            ) {
                Text(text = "Président", fontSize = 18.sp, color = Color.White)
            }

            Button(
                onClick = { /* TODO: Navigate to Connect a GamiXer */ },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Tertiary)
            ) {
                Text(text = "Connecter un GamiXer", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
