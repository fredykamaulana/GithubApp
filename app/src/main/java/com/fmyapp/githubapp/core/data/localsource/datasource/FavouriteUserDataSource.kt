package com.fmyapp.githubapp.core.data.localsource.datasource

import com.fmyapp.githubapp.core.data.localsource.dao.FavouriteUserDao
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.data.utils.SafeDbCall
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class FavouriteUserDataSource @Inject constructor(private val dao: FavouriteUserDao) :
    SafeDbCall() {

    fun getAllFavouriteUser(): Flow<Result<List<FavouriteUserEntity>>> {
        return safeDbCall { dao.getAllFavouriteUser().first() }
    }

    suspend fun setUserAsFavourite(user: FavouriteUserEntity): Flow<Result<Long>> {
        return safeDbCall { dao.setUserAsFavourite(user = user) }
    }

    suspend fun deleteFavouriteUser(user: FavouriteUserEntity): Flow<Result<Int>> {
        return safeDbCall { dao.deleteFavouriteUser(user = user) }
    }
}