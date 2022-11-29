package com.fmyapp.githubapp.core.di

import com.fmyapp.githubapp.core.data.services.GithubUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServicesModule {

    @Provides
    @Singleton
    fun provideGithubUserService(retrofit: Retrofit): GithubUserService =
        retrofit.create(GithubUserService::class.java)
}