package com.example.gamemixandroid.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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


import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import com.example.gamemixandroid.telemetry.Telemetry
import com.example.gamemixandroid.telemetry.Telemetry.logCurrentScreen

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        logCurrentScreen("HomeScreen")
    }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "homeScreen",
        modifier = Modifier.semantics { contentDescription = "Navigation principale de GameMix" }
    ) {
        composable("homeScreen") {
            Telemetry.setScreen("HomeScreen")
            HomeContent(navController)
        }

        composable("GameListScreen") {
            Telemetry.setScreen("GameListScreen")
            GameListScreen(viewModel, navController)
        }

        composable("BeloteScreenGame") {
            Telemetry.setScreen("BeloteScreenGame")
            SetGameScreen(
                maxPlayers = 4,
                minPlayers = 4,
                gameName = "Belote",
                navController = navController,
                viewModel = viewModel()
            )
        }

        composable("PresidentScreenGame") {
            Telemetry.setScreen("PresidentScreenGame")
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
            arguments = listOf(navArgument("playersJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("playersJson")
                ?.let { URLDecoder.decode(it, "UTF-8") } ?: "[]"
            val players: List<Player> = Json.decodeFromString(json)
            val gameViewModel: GameViewModel = viewModel()
            Telemetry.setScreen("PresidentGame")
            GameScreen(players, gameViewModel, navController)
        }

        composable(
            route = "beloteGame/{playersJson}",
            arguments = listOf(navArgument("playersJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("playersJson")
                ?.let { URLDecoder.decode(it, "UTF-8") } ?: "[]"
            val players: List<Player> = Json.decodeFromString(json)
            val gameViewModel: GameViewModel = viewModel()
            Telemetry.setScreen("BeloteGame")
            GameScreen(players, gameViewModel, navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    LaunchedEffect(Unit) {
        logCurrentScreen("HomeScreen")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .testTag("HomeScreen")
            .semantics { contentDescription = "Écran d'accueil GameMix" }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.gamemixlogo_transparent),
                contentDescription = "Logo de l'application GameMix",
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            )

            // Bouton Jouer
            CustomButton(
                text = "JOUER !",
                onClick = { navController.navigate("GameListScreen") },
                height = 70,
                fontSize = 24.sp,
                modif = Modifier
                    .testTag("PlayButton")
                    .semantics { contentDescription = "Bouton jouer - accéder à la liste des jeux" }
            )
        }
    }
}

