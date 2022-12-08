package com.fmyapp.githubapp.core.data.repositories.setting

import com.fmyapp.githubapp.core.data.localsource.datasource.SettingPreferenceDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingPreferenceRepositoryImpl @Inject constructor(
    private val settingPreferenceDataSource: SettingPreferenceDataSource
) : SettingPreferenceRepository {
    override fun getThemeSetting(): Flow<Boolean> {
        return settingPreferenceDataSource.getThemeSetting()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingPreferenceDataSource.saveThemeSetting(isDarkModeActive = isDarkModeActive)
    }
}