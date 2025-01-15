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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamemixandroid.R
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.Primary

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val navController = rememberNavController()

    // Navigation Host setup
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("GameListScreen") {
            GameListScreen(viewModel) // Ensure GameListScreen is implemented
        }
        composable("homeScreen") {
            HomeContent(navController) // Delegate the home screen content to another function
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background) // Set the background color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Section
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent), // Replace with your logo resource ID
                contentDescription = "GameMix Logo",
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth()
            )

            // Welcome Text Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenue !",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Vous voici sur GAMEMIX !\nUne application qui va changer vos soirées entre amis !",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Button Section
            Button(
                onClick = { navController.navigate("GameListScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Primary)
            ) {
                Text(text = "Commencer →", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
