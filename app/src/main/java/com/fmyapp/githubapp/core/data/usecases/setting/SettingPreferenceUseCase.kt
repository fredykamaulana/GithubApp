package com.fmyapp.githubapp.core.data.usecases.setting

import kotlinx.coroutines.flow.Flow

interface SettingPreferenceUseCase {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}