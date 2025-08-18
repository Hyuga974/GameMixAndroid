package com.example.gamemixandroid

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gamemixandroid.View.HomeScreen
import com.example.gamemixandroid.telemetry.Telemetry
import com.example.gamemixandroid.ui.theme.GameMixAndroidTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        FirebaseApp.initializeApp(this)
        firebaseAnalytics = Firebase.analytics
        Telemetry.init(applicationContext)


        val prefs = getSharedPreferences("gamix_prefs", MODE_PRIVATE)
        var deviceId = prefs.getString("device_id", null)

        if (deviceId == null) {
            deviceId = java.util.UUID.randomUUID().toString()
            prefs.edit().putString("device_id", deviceId).apply()
        }

        FirebaseCrashlytics.getInstance().setUserId("Device_$deviceId")

        Firebase.crashlytics.log("App started")

        val trace = FirebasePerformance.getInstance().newTrace("startup_trace")
        trace.start()
        Thread.sleep(300)
        trace.stop()

        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("feature_bluetooth_enabled" to true))

        // Charger Remote Config en asynchrone
        CoroutineScope(Dispatchers.IO).launch {
            remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val bluetoothEnabled = remoteConfig.getBoolean("feature_bluetooth_enabled")
                    Log.d("RemoteConfig", "Bluetooth enabled: $bluetoothEnabled")
                }
            }
        }

        // 5️⃣ UI principale
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
