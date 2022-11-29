package com.fmyapp.githubapp.core.data.localsource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fmyapp.githubapp.core.data.localsource.dao.FavouriteUserDao
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity

const val dbVersion = 1
const val exportScheme = false

@Database(
    entities = [
        FavouriteUserEntity::class
    ], version = dbVersion, exportSchema = exportScheme
)

abstract class GithubUserDb : RoomDatabase() {
    abstract fun favouriteUserDao(): FavouriteUserDao
}