package com.fmyapp.githubapp.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.usecases.favouriteuser.FavouriteUserUseCase
import com.fmyapp.githubapp.core.data.usecases.getusers.GetUserListUseCase
import com.fmyapp.githubapp.core.data.usecases.searchuser.SearchUserUseCase
import com.fmyapp.githubapp.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val searchUserUseCase: SearchUserUseCase,
    private val favouriteUserUseCase: FavouriteUserUseCase
) : ViewModel() {

    private val _userListResult = MutableLiveData<Result<List<UserListItemDto>>>()
    val userListResult get() = _userListResult

    fun getUserList() {
        _userListResult.value = Result.Loading
        viewModelScope.launch {
            _userListResult.value = getUserListUseCase.getUserList().last()
        }
    }

    fun setUserListResultEmpty() {
        _userListResult.value = Result.Empty
    }

    private val _searchUserResult = MutableLiveData<Result<SearchUserResultDto>>()
    val searchUserResult get() = _searchUserResult

    fun searchUser(username: String) {
        _searchUserResult.value = Result.Loading
        viewModelScope.launch {
            _searchUserResult.value = searchUserUseCase.searchUser(username = username).last()
        }
    }

    fun setSearchUserResultEmpty() {
        _searchUserResult.value = Result.Empty
    }

    private val _setUserAsFavourite = MutableLiveData<Result<Long>>()
    val setUserAsFavourite: LiveData<Result<Long>> get() = _setUserAsFavourite

    fun setUserAsFavourite(user: FavouriteUserEntity) {
        _setUserAsFavourite.value = Result.Loading
        viewModelScope.launch {
            _setUserAsFavourite.value = favouriteUserUseCase.setUserAsFavourite(user = user).last()
        }
    }

    fun setUserFavouriteResultAsNeutral() {
        _setUserAsFavourite.value = Result.Empty
    }
}