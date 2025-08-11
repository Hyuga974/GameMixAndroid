package com.example.gamemixandroid.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamemixandroid.Model.Player
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.ViewModel.GameViewModel
import com.example.gamemixandroid.ViewModel.HomeViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.ui.home.GameListScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val navController = rememberNavController()

    // Navigation Host setup
    NavHost(navController = navController, startDestination = "homeScreen") {

        composable("homeScreen") {
            HomeContent(navController)
        }

        composable("GameListScreen") {
            GameListScreen(viewModel, navController)
        }

        composable("BeloteScreenGame") {
            SetGameScreen(
                maxPlayers = 4,
                minPlayers = 4,
                gameName = "Belote",
                navController = navController,
                viewModel = viewModel()

            )
        }
        composable("PresidentScreenGame") {
            SetGameScreen(
                maxPlayers = 7,
                minPlayers = 2,
                gameName = "Président",
                navController = navController,
                viewModel = viewModel()
            )
        }

        composable(
            route = "présidentGame/{playersJson}",
            arguments = listOf(navArgument("playersJson") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            // print the playersJson argument
            val json = backStackEntry.arguments?.getString("playersJson")
                ?.let { URLDecoder.decode(it, "UTF-8") } ?: "[]"
            println("playersJson reçu : $json")
            val players: List<Player> = Json.decodeFromString(json)
            val gameViewModel: GameViewModel = viewModel()
            GameScreen(playerList = players, viewModel = gameViewModel, navController = navController)
        }

        // 🔽 Route dynamique avec paramètre JSON encodé pour la liste des joueurs
        composable(
            route = "beloteGame/{playersJson}",
            arguments = listOf(navArgument("playersJson") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            // print the playersJson argument
            val json = backStackEntry.arguments?.getString("playersJson")
                ?.let { URLDecoder.decode(it, "UTF-8") } ?: "[]"
            println("playersJson reçu : $json")
            val players: List<Player> = Json.decodeFromString(json)
            val gameViewModel: GameViewModel = viewModel()
            GameScreen(playerList = players, viewModel = gameViewModel, navController = navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background) // Set the background color
            .testTag("HomeScreen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo Section
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent), // Replace with your logo resource ID
                contentDescription = "GameMix Logo",
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            )

            // Button Section
            CustomButton(
                "JOUER !",
                onClick = { navController.navigate("GameListScreen") },
                height = 70,
                fontSize = 24.sp,
                modif = Modifier.testTag("PlayButton")
            )

        }
    }
}
