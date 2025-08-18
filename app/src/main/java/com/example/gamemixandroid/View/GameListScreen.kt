package com.example.ui.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamemixandroid.R
import com.example.gamemixandroid.View.Component.CustomButton
import com.example.gamemixandroid.ViewModel.BluetoothViewModel
import com.example.gamemixandroid.ViewModel.HomeViewModel
import com.example.gamemixandroid.ui.theme.Background
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Tertiary
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import com.example.gamemixandroid.telemetry.Telemetry
import com.example.gamemixandroid.telemetry.Telemetry.logCurrentScreen

@Composable
fun GameListScreen(viewModel: HomeViewModel = viewModel(), navController: NavController) {

    LaunchedEffect(Unit) {
        logCurrentScreen("GameListScreen")
    }
    val bluetoothViewModel: BluetoothViewModel = viewModel()
    val context = LocalContext.current
    val activity = context as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
            .testTag("GameListScreen")
            .semantics { contentDescription = "Écran d'accueil GameMix" },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Section
        Image(
            painter = painterResource(id = R.drawable.gamemixlogo_transparent),
            contentDescription = "Logo de l'application GameMix",
            modifier = Modifier
                .height(350.dp)
                .width(350.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            CustomButton(
                "Belote",
                textColor = Primary,
                onClick = { navController.navigate("BeloteScreenGame") },
                height = 60,
                width=0.8f,
                fontSize = 20.sp,
                backgroundColor = NoName,
                modif = Modifier
                    .testTag("BeloteButton")
                    .semantics { contentDescription = "Lancer une partie de Belote" }
            )
            CustomButton(
                "Président",
                textColor = Primary,
                onClick = { navController.navigate("PrésidentScreenGame") },
                height = 60,
                width=0.8f,
                fontSize = 20.sp,
                backgroundColor = NoName,
                modif = Modifier
                    .testTag("PresidentButton")
                    .semantics { contentDescription = "Lancer une partie de Président" }
            )
            CustomButton(
                "Connecter un GameMixer !",
                onClick = {
                    Telemetry.trackAction(
                        feature = "bluetooth_connection",
                        step = "connect",
                        expected = "device_connected",
                        severityOnError = "Majeur"
                    ){if (!bluetoothViewModel.hasBluetoothPermission(context)) {
                        Log.d("BluetoothPermission", "Permissions demandées")
                        bluetoothViewModel.requestBluetoothPermission(activity)
                    } else {
                        Log.d("BluetoothPermission", "Permissions déjà accordées")
                    }}
                },
                height = 60,
                width=0.8f,
                fontSize = 20.sp,
                backgroundColor = Tertiary,
                modif = Modifier
                    .semantics { contentDescription = "Connecter un appareil GameMixer via Bluetooth" }
            )
        }
    }
}
