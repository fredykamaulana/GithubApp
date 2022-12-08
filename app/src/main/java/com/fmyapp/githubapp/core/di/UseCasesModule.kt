package com.fmyapp.githubapp.core.di

import com.fmyapp.githubapp.core.data.usecases.favouriteuser.FavouriteUserInteractor
import com.fmyapp.githubapp.core.data.usecases.favouriteuser.FavouriteUserUseCase
import com.fmyapp.githubapp.core.data.usecases.getfollowers.GetUserFollowersInteractor
import com.fmyapp.githubapp.core.data.usecases.getfollowers.GetUserFollowersUseCase
import com.fmyapp.githubapp.core.data.usecases.getfollowing.GetUserFollowingInteractor
import com.fmyapp.githubapp.core.data.usecases.getfollowing.GetUserFollowingUseCase
import com.fmyapp.githubapp.core.data.usecases.getuserdetail.GetUserDataInteractor
import com.fmyapp.githubapp.core.data.usecases.getuserdetail.GetUserDataUseCase
import com.fmyapp.githubapp.core.data.usecases.getusers.GetUserListInteractor
import com.fmyapp.githubapp.core.data.usecases.getusers.GetUserListUseCase
import com.fmyapp.githubapp.core.data.usecases.searchuser.SearchUserInteractor
import com.fmyapp.githubapp.core.data.usecases.searchuser.SearchUserUseCase
import com.fmyapp.githubapp.core.data.usecases.setting.SettingPreferenceInteractor
import com.fmyapp.githubapp.core.data.usecases.setting.SettingPreferenceUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {
    @Binds
    fun bindGetUserListUseCase(interactor: GetUserListInteractor): GetUserListUseCase

    @Binds
    fun bindGetUserDataUseCase(interactor: GetUserDataInteractor): GetUserDataUseCase

    @Binds
    fun bindGetUserFollowersUseCase(interactor: GetUserFollowersInteractor): GetUserFollowersUseCase

    @Binds
    fun bindGetUserFollowingUseCase(interactor: GetUserFollowingInteractor): GetUserFollowingUseCase

    @Binds
    fun bindSearchUserUseCase(interactor: SearchUserInteractor): SearchUserUseCase

    @Binds
    fun bindFavouriteUserUseCase(interactor: FavouriteUserInteractor): FavouriteUserUseCase

    @Binds
    fun bindSettingPreferenceUseCase(interactor: SettingPreferenceInteractor): SettingPreferenceUseCase
}