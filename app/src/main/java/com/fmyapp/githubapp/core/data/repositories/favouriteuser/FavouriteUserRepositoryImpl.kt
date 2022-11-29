package com.fmyapp.githubapp.core.data.repositories.favouriteuser

import com.fmyapp.githubapp.core.data.localsource.datasource.FavouriteUserDataSource
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FavouriteUserRepositoryImpl @Inject constructor(
    private val favouriteUserDataSource: FavouriteUserDataSource
) : FavouriteUserRepository {

    override fun getAllFavouriteUser(): Flow<Result<List<FavouriteUserEntity>>> {
        return favouriteUserDataSource.getAllFavouriteUser()
    }

    override suspend fun setUserAsFavourite(user: FavouriteUserEntity): Flow<Result<Long>> {
        return favouriteUserDataSource.setUserAsFavourite(user = user)
    }

    override suspend fun deleteFavouriteUser(user: FavouriteUserEntity): Flow<Result<Int>> {
        return favouriteUserDataSource.deleteFavouriteUser(user = user)
    }
}