package com.fmyapp.githubapp.core.data.localsource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteUserDao {

    @Query("SELECT * FROM saved_github_user WHERE favourite = 1")
    fun getAllFavouriteUser(): Flow<List<FavouriteUserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUserAsFavourite(user: FavouriteUserEntity): Long

    @Delete
    suspend fun deleteFavouriteUser(user: FavouriteUserEntity): Int
}