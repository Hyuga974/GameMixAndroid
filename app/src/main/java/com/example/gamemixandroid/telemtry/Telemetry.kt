package com.example.gamemixandroid.telemetry

import android.os.Build
import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.ktx.BuildConfig
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.util.UUID
import kotlin.system.exitProcess

object Telemetry {
    private var sessionId: String = "session-${UUID.randomUUID()}"
    private var lastScreen: String = "unknown"
    private var errorMsg: String = "No error"
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // Init
    fun init() {
        firebaseAnalytics = Firebase.analytics
        // Configuration de base pour Crashlytics
        Firebase.crashlytics.apply {
            setCustomKey("session_id", sessionId)
            setCustomKey("app_version", BuildConfig.VERSION_NAME)
            setCustomKey("os_version", Build.VERSION.RELEASE ?: "unknown")
            setCustomKey("device_model", Build.MODEL ?: "unknown")
        }
        // Log start session
        Firebase.analytics.logEvent("session_start") {
            param("session_id", sessionId)
            param("screen", lastScreen)
            param("error_message", errorMsg)
        }

        // Manage  exceptions
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            errorMsg = throwable.message ?: throwable.javaClass.simpleName.take(100)
            Firebase.analytics.logEvent("app_exception") {
                param("session_id", sessionId)
                param("screen", lastScreen)
                param("error_message", errorMsg)
            }
            Thread.sleep(3000)
            Firebase.crashlytics.apply {
                setCustomKey("last_screen", lastScreen)
                setCustomKey("error_message", errorMsg)
                recordException(throwable)
            }
            Log.e("Telemetry", "Uncaught exception on $lastScreen: $errorMsg")
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(2)
        }

    }

    fun setScreen(screenName: String) {
        lastScreen = screenName
        Firebase.crashlytics.setCustomKey("current_screen", screenName)
        Firebase.analytics.logEvent("gm_screen") {
            param("screen", screenName)
            param("session_id", sessionId)
            param("error_message", errorMsg)
        }
    }

    fun expected(feature: String, expected: String, step: String) {
        Firebase.analytics.logEvent("expected") {
            param("feature", feature)
            param("step", step)
            param("result_expected", expected)
            param("screen", lastScreen)
            param("error_message", errorMsg)
            param("session_id", sessionId)
        }
        Firebase.crashlytics.log("EXPECTED[$feature/$step] -> $expected")
    }


    fun success(feature: String, step: String, obtained: String? = null) {
        Firebase.analytics.logEvent("result") {
                param("feature", feature)
                param("step" , step)
                param("status" , "success")
                param("result_obtained" , obtained?:"null")
                param("screen" , lastScreen)
                param("error_message" , errorMsg)
                param("session_id" , sessionId)
        }
        Firebase.crashlytics.log("SUCCESS[$feature/$step] ${obtained ?: ""}")
    }

    fun failure(
        feature: String,
        step: String,
        obtained: String,
        severity: String = "Majeur",
        t: Throwable? = null
    ) {
        errorMsg = obtained
        Firebase.analytics.logEvent("result") {
            param("feature", feature)
            param("step", step)
            param("status", "failure")
            param("result_obtained", obtained)
            param("severity", severity)
            param("screen", lastScreen)
            param("error_message", errorMsg)
            param("session_id", sessionId)
        }
        Firebase.crashlytics.apply {
            setCustomKey("feature", feature)
            setCustomKey("step", step)
            setCustomKey("severity", severity)
            setCustomKey("result_obtained", obtained)
            setCustomKey("error_message", errorMsg)
            if (t != null) recordException(t)
            else log("FAILURE[$feature/$step] $obtained (severity=$severity)")
        }
    }

    inline fun <T> trackAction(
        feature: String,
        step: String,
        expected: String,
        severityOnError: String = "Majeur",
        block: () -> T
    ): T? {
        expected(feature, expected, step)
        return try {
            val result = block()
            success(feature, step, obtained = "ok")
            result
        } catch (e: Exception) {
            Log.e("Telemetry", "Action failed: $feature/$step", e)
            failure(
                feature,
                step,
                obtained = e.localizedMessage ?: "unknown",
                severity = severityOnError,
                t = e
            )
            null
        }
    }
}
