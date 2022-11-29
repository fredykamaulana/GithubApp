package com.fmyapp.githubapp.favouriteuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.usecases.favouriteuser.FavouriteUserUseCase
import com.fmyapp.githubapp.core.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@HiltViewModel
class FavouriteUserViewModel @Inject constructor(
    private val favouriteUserUseCase: FavouriteUserUseCase
) : ViewModel() {

    private val _getAllFavouriteUser = MutableLiveData<Result<List<FavouriteUserEntity>>>()
    val getAllFavouriteUser: LiveData<Result<List<FavouriteUserEntity>>> get() = _getAllFavouriteUser

    fun getAllFavouriteUser() {
        _getAllFavouriteUser.value = Result.Loading
        viewModelScope.launch {
            _getAllFavouriteUser.value = favouriteUserUseCase.getAllFavouriteUser().last()
        }
    }

    private val _setUserAsFavourite = MutableLiveData<Result<Long>>()
    val setUserAsFavourite: LiveData<Result<Long>> get() = _setUserAsFavourite

    fun setUserAsFavourite(user: FavouriteUserEntity) {
        _setUserAsFavourite.value = Result.Loading
        viewModelScope.launch {
            _setUserAsFavourite.value = favouriteUserUseCase.setUserAsFavourite(user = user).last()
        }
    }

    private val _deleteFavouriteUser = MutableLiveData<Result<Int>>()
    val deleteFavouriteUser: LiveData<Result<Int>> get() = _deleteFavouriteUser

    fun deleteFavouriteUser(user: FavouriteUserEntity) {
        _deleteFavouriteUser.value = Result.Loading
        viewModelScope.launch {
            _deleteFavouriteUser.value =
                favouriteUserUseCase.deleteFavouriteUser(user = user).last()
        }
    }
}