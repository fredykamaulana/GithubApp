package com.fmyapp.githubapp.core.di

import com.fmyapp.githubapp.core.data.repositories.favouriteuser.FavouriteUserRepository
import com.fmyapp.githubapp.core.data.repositories.favouriteuser.FavouriteUserRepositoryImpl
import com.fmyapp.githubapp.core.data.repositories.getfollowers.GetUserFollowersRepository
import com.fmyapp.githubapp.core.data.repositories.getfollowers.GetUserFollowersRepositoryImpl
import com.fmyapp.githubapp.core.data.repositories.getfollowing.GetUserFollowingRepository
import com.fmyapp.githubapp.core.data.repositories.getfollowing.GetUserFollowingRepositoryImpl
import com.fmyapp.githubapp.core.data.repositories.getuserdata.GetUserDataRepository
import com.fmyapp.githubapp.core.data.repositories.getuserdata.GetUserDataRepositoryImpl
import com.fmyapp.githubapp.core.data.repositories.getusers.GetUserListRepository
import com.fmyapp.githubapp.core.data.repositories.getusers.GetUserListRepositoryImpl
import com.fmyapp.githubapp.core.data.repositories.searchuser.SearchUserRepository
import com.fmyapp.githubapp.core.data.repositories.searchuser.SearchUserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoriesModule {
    @Binds
    fun bindGetUserListRepository(repositoryImpl: GetUserListRepositoryImpl): GetUserListRepository

    @Binds
    fun bindGetUserDataRepository(repositoryImpl: GetUserDataRepositoryImpl): GetUserDataRepository

    @Binds
    fun bindGetUserFollowersRepository(repositoryImpl: GetUserFollowersRepositoryImpl): GetUserFollowersRepository

    @Binds
    fun bindGetUserFollowingRepository(repositoryImpl: GetUserFollowingRepositoryImpl): GetUserFollowingRepository

    @Binds
    fun bindSearchUserRepository(repositoryImpl: SearchUserRepositoryImpl): SearchUserRepository

    @Binds
    fun bindFavouriteUserRepository(repositoryImpl: FavouriteUserRepositoryImpl): FavouriteUserRepository
}