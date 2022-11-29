package com.fmyapp.githubapp.core.data.usecases.favouriteuser

import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.repositories.favouriteuser.FavouriteUserRepository
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavouriteUserInteractor @Inject constructor(
    private val favouriteUserRepository: FavouriteUserRepository
) : FavouriteUserUseCase {

    override fun getAllFavouriteUser(): Flow<Result<List<FavouriteUserEntity>>> {
        return favouriteUserRepository.getAllFavouriteUser()
    }

    override suspend fun setUserAsFavourite(user: FavouriteUserEntity): Flow<Result<Long>> {
        return favouriteUserRepository.setUserAsFavourite(user = user)
    }

    override suspend fun deleteFavouriteUser(user: FavouriteUserEntity): Flow<Result<Int>> {
        return favouriteUserRepository.deleteFavouriteUser(user = user)
    }
}