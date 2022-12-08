package com.fmyapp.githubapp.core.data.localsource.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferenceDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PREFERENCE_KEY_THEME] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[PREFERENCE_KEY_THEME] = isDarkModeActive
        }
    }

    companion object {
        private val PREFERENCE_KEY_THEME = booleanPreferencesKey("theme_setting")
    }
}