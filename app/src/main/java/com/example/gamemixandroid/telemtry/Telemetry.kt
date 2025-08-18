package com.example.gamemixandroid.telemetry

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.os.bundleOf
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

    fun init(context: Context) {
        Firebase.crashlytics.setCustomKey("session_id", sessionId)
        Firebase.crashlytics.setCustomKey("app_version", BuildConfig.VERSION_NAME)
        Firebase.crashlytics.setCustomKey("os_version", Build.VERSION.RELEASE ?: "unknown")
        Firebase.crashlytics.setCustomKey("device_model", Build.MODEL ?: "unknown")

        // (Optionnel) tracer le démarrage de session
        Firebase.analytics.logEvent(TelemetryEvent.RESULT) {
            param(TelemetryParam.FEATURE, "session")
            param(TelemetryParam.STEP, "start")
            param(TelemetryParam.STATUS, "success")
            param(TelemetryParam.SESSION_ID, sessionId)
        }

        // 🚨 Global crash handler
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            // Envoi vers Crashlytics
            FirebaseCrashlytics.getInstance().recordException(throwable)

            // Extra info
            val errorMsg = throwable.message ?: throwable.javaClass.simpleName
            println("Telemetry : $errorMsg")
            print("Telemetry : ${throwable.cause}")
            println("Telemetry : Last screen was $lastScreen")



            // Envoi d’un event Firebase Analytics → dispo dans BigQuery
            Firebase.analytics.logEvent("app_exception", bundleOf(
                "error_message" to errorMsg,
                "current_screen" to lastScreen,
                TelemetryParam.SESSION_ID to sessionId
            ))
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(2)
        }
    }

    fun setScreen(screenName: String) {
        lastScreen = screenName
        Firebase.analytics.logEvent(TelemetryEvent.SCREEN) {
            param(TelemetryParam.SCREEN, screenName)
            param(TelemetryParam.SESSION_ID, sessionId)
        }
    }

    /** On enregistre ce que l’on attend d’une action critique */
    fun expected(feature: String, expected: String, step: String) {
        Firebase.analytics.logEvent(TelemetryEvent.EXPECTED) {
            param(TelemetryParam.FEATURE, feature)
            param(TelemetryParam.STEP, step)
            param(TelemetryParam.RESULT_EXPECTED, expected)
            param(TelemetryParam.SESSION_ID, sessionId)
        }
        Firebase.crashlytics.log("EXPECTED[$feature/$step] -> $expected")
    }

    /** Succès : on précise ce qui s’est passé concrètement */
    fun success(feature: String, step: String, obtained: String? = null) {
        Firebase.analytics.logEvent(TelemetryEvent.RESULT) {
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

    /** Échec non fatal : on remonte message + sévérité + exception */
    fun failure(
        feature: String,
        step: String,
        obtained: String,
        severity: String = "Majeur",
        t: Throwable? = null
    ) {
        Firebase.analytics.logEvent(TelemetryEvent.RESULT) {
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

    /** Helper pour entourer une action et tracer attempt/success/failure */
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

    fun logCurrentScreen(screenName: String) {
        lastScreen = screenName
        FirebaseCrashlytics.getInstance().setCustomKey("current_screen", screenName)
    }
}
