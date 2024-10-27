package com.example.appsetting

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class DataStorePreferences(private val context: Context) {

    // key cho các setting
    private val wakeForMealsKey = booleanPreferencesKey("wake_for_meals")
    private val favoriteCityKey = stringPreferencesKey("favorite_city")
    private val travelClassKey = stringPreferencesKey("travel_class")
    private val notificationsEnabledKey = booleanPreferencesKey("notifications_enabled")

    // get set
    val wakeForMeals: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[wakeForMealsKey] ?: false
        }

    val favoriteCity: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[favoriteCityKey] ?: ""
        }

    val travelClass: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[travelClassKey] ?: "Economy"
        }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[notificationsEnabledKey] ?: false }

    suspend fun setWakeForMeals(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[wakeForMealsKey] = value
        }
    }

    suspend fun setFavoriteCity(value: String) {
        context.dataStore.edit { preferences ->
            preferences[favoriteCityKey] = value
        }
    }

    suspend fun setTravelClass(value: String) {
        context.dataStore.edit { preferences ->
            preferences[travelClassKey] = value
        }
    }

    suspend fun setNotificationsEnabled(value: Boolean) {
        context.dataStore.edit { preferences -> preferences[notificationsEnabledKey] = value }
    }
}