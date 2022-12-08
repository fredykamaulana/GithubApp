package com.fmyapp.githubapp.core.data.usecases.setting

import com.fmyapp.githubapp.core.data.repositories.setting.SettingPreferenceRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingPreferenceInteractor @Inject constructor(
    private val repository: SettingPreferenceRepository
) : SettingPreferenceUseCase {
    override fun getThemeSetting(): Flow<Boolean> {
        return repository.getThemeSetting()
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        repository.saveThemeSetting(isDarkModeActive = isDarkModeActive)
    }
}