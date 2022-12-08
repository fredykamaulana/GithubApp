package com.fmyapp.githubapp.core.data.repositories.setting

import kotlinx.coroutines.flow.Flow

interface SettingPreferenceRepository {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}