package com.fmyapp.githubapp.userdetail.usernetwork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.usecases.getfollowers.GetUserFollowersUseCase
import com.fmyapp.githubapp.core.data.usecases.getfollowing.GetUserFollowingUseCase
import com.fmyapp.githubapp.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@HiltViewModel
class UserNetworkViewModel @Inject constructor(
    private val getUserFollowersUseCase: GetUserFollowersUseCase,
    private val getUserFollowingUseCase: GetUserFollowingUseCase
) : ViewModel() {

    private val _userFollowersResult = MutableLiveData<Result<List<UserListItemDto>>>()
    val userFollowersResult get() = _userFollowersResult

    fun getUserFollowers(username: String) {
        _userFollowersResult.value = Result.Loading
        viewModelScope.launch {
            _userFollowersResult.value = getUserFollowersUseCase.getUserFollowers(username).last()
        }
    }

    private val _userFollowingResult = MutableLiveData<Result<List<UserListItemDto>>>()
    val userFollowingResult get() = _userFollowingResult

    fun getUserFollowing(username: String) {
        _userFollowingResult.value = Result.Loading
        viewModelScope.launch {
            _userFollowingResult.value = getUserFollowingUseCase.getUserFollowing(username).last()
        }
    }
}