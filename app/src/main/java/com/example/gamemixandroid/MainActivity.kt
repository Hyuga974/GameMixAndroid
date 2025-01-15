package com.example.gamemixandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamemixandroid.ui.theme.GameMixAndroidTheme
import com.example.ui.home.GameListScreen
import com.example.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen();
        setContent {
            GameMixAndroidTheme {
                HomeScreen()

            }
        }
    }
}
