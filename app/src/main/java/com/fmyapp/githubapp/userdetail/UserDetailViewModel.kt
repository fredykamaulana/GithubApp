package com.fmyapp.githubapp.userdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.usecases.getuserdetail.GetUserDataUseCase
import com.fmyapp.githubapp.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _userDataResult = MutableLiveData<Result<UserDataDto>>()
    val userDataResult get() = _userDataResult

    fun getUserData(username: String) {
        _userDataResult.value = Result.Loading
        viewModelScope.launch {
            _userDataResult.value = getUserDataUseCase.getUserData(username).last()
        }
    }
}