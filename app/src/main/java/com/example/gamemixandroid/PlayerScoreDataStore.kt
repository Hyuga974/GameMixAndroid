package com.example.gamemixandroid

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.text.set

val Context.dataStore by preferencesDataStore("player_scores")

object PlayerScoreCache {
    private val SCORE_KEY = intPreferencesKey("player_score_")

    suspend fun saveScore(context: Context, playerId: String, score: Int) {
        context.dataStore.edit { prefs ->
            prefs[intPreferencesKey(SCORE_KEY.name + playerId)] = score
        }
    }

    suspend fun setScore(context: Context, playerId: String, newScore: Int) {
        context.dataStore.edit { preferences ->
            preferences[intPreferencesKey("score_$playerId")] = newScore
        }
    }

    fun getScore(context: Context, playerId: String): Flow<Int> =
        context.dataStore.data.map { prefs ->
            prefs[intPreferencesKey(SCORE_KEY.name + playerId)] ?: 0
        }
}