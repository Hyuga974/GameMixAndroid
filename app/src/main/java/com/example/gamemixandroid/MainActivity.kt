package com.example.gamemixandroid

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gamemixandroid.View.HomeScreen
import com.example.gamemixandroid.ui.theme.GameMixAndroidTheme

class MainActivity : ComponentActivity() {

    private val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen();
        setContent {
            GameMixAndroidTheme {
                HomeScreen()

            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_BLUETOOTH_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("BluetoothPermission", "Permission accordée")
            } else {
                Log.d("BluetoothPermission", "Permission refusée")
            }
        }
    }
}
