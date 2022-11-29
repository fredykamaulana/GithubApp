package com.fmyapp.githubapp.core.data.usecases.favouriteuser

import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface FavouriteUserUseCase {

    fun getAllFavouriteUser(): Flow<Result<List<FavouriteUserEntity>>>

    suspend fun setUserAsFavourite(user: FavouriteUserEntity): Flow<Result<Long>>

    suspend fun deleteFavouriteUser(user: FavouriteUserEntity): Flow<Result<Int>>
}