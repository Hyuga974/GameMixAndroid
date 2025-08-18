package com.example.gamemixandroid.telemetry

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.BuildConfig
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.util.UUID
import kotlin.system.exitProcess

object Telemetry {

    private var sessionId: String = "session-" + UUID.randomUUID().toString()
    private var lastScreen: String = "unknown"
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun init() {
        firebaseAnalytics = Firebase.analytics

        // Crashlytics context keys
        Firebase.crashlytics.setCustomKey("session_id", sessionId)
        Firebase.crashlytics.setCustomKey("app_version", BuildConfig.VERSION_NAME)
        Firebase.crashlytics.setCustomKey("os_version", Build.VERSION.RELEASE ?: "unknown")
        Firebase.crashlytics.setCustomKey("device_model", Build.MODEL ?: "unknown")

        // Session start event
        firebaseAnalytics.logEvent(TelemetryEvent.RESULT) {
            param(TelemetryParam.FEATURE, "session")
            param(TelemetryParam.STEP, "start")
            param(TelemetryParam.STATUS, "success")
            param(TelemetryParam.SESSION_ID, sessionId)
        }

        // Global crash handler
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            val errorMsg = throwable.message ?: throwable.javaClass.simpleName
            val crashlytics = FirebaseCrashlytics.getInstance()

            // Crashlytics
            crashlytics.setCustomKey("last_screen", lastScreen)
            crashlytics.recordException(throwable)

            // Analytics custom event
            firebaseAnalytics.logEvent("app_exception") {
                param("error_message", errorMsg)
                param(FirebaseAnalytics.Param.SCREEN_NAME, lastScreen) // 👈 standard
                param(TelemetryParam.SESSION_ID, sessionId)
            }

            Log.e("Telemetry", "Uncaught exception on $lastScreen: $errorMsg")

            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(2)
        }
    }

    fun setScreen(screenName: String, screenClass: String = "ComposeScreen") {
        lastScreen = screenName

        // Firebase standard SCREEN_VIEW event
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass)
            param(TelemetryParam.SESSION_ID, sessionId)
        }

        // Crashlytics key for correlation
        FirebaseCrashlytics.getInstance().setCustomKey("current_screen", screenName)
    }

    fun expected(feature: String, expected: String, step: String) {
        firebaseAnalytics.logEvent(TelemetryEvent.EXPECTED) {
            param(TelemetryParam.FEATURE, feature)
            param(TelemetryParam.STEP, step)
            param(TelemetryParam.RESULT_EXPECTED, expected)
            param(TelemetryParam.SESSION_ID, sessionId)
        }
        Firebase.crashlytics.log("EXPECTED[$feature/$step] -> $expected")
    }

    fun success(feature: String, step: String, obtained: String? = null) {
        firebaseAnalytics.logEvent(TelemetryEvent.RESULT) {
            param(TelemetryParam.FEATURE, feature)
            param(TelemetryParam.STEP, step)
            param(TelemetryParam.STATUS, "success")
            if (!obtained.isNullOrBlank()) {
                param(TelemetryParam.RESULT_OBTAINED, obtained)
            }
            param(TelemetryParam.SESSION_ID, sessionId)
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
        firebaseAnalytics.logEvent(TelemetryEvent.RESULT) {
            param(TelemetryParam.FEATURE, feature)
            param(TelemetryParam.STEP, step)
            param(TelemetryParam.STATUS, "failure")
            param(TelemetryParam.RESULT_OBTAINED, obtained)
            param(TelemetryParam.SEVERITY, severity)
            param(TelemetryParam.SESSION_ID, sessionId)
        }

        Firebase.crashlytics.setCustomKey("feature", feature)
        Firebase.crashlytics.setCustomKey("step", step)
        Firebase.crashlytics.setCustomKey("severity", severity)
        Firebase.crashlytics.setCustomKey("result_obtained", obtained)

        if (t != null) {
            Firebase.crashlytics.recordException(t) // non fatal
        } else {
            Firebase.crashlytics.log("FAILURE[$feature/$step] $obtained (severity=$severity)")
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
            failure(feature, step, obtained = e.localizedMessage ?: "unknown", severity = severityOnError, t = e)
            null
        }
    }
}
