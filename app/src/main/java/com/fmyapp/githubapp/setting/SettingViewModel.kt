package com.fmyapp.githubapp.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fmyapp.githubapp.core.data.usecases.setting.SettingPreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingPreferenceUseCase: SettingPreferenceUseCase
) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferenceUseCase.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingPreferenceUseCase.saveThemeSetting(isDarkModeActive)
        }
    }
}