package com.fmyapp.githubapp.core.di

import com.fmyapp.githubapp.core.data.localsource.dao.FavouriteUserDao
import com.fmyapp.githubapp.core.data.localsource.database.GithubUserDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideFavouriteUserDao(githubUserDb: GithubUserDb): FavouriteUserDao {
        return githubUserDb.favouriteUserDao()
    }
}