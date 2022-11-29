package com.fmyapp.githubapp.core.data.services

import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserService {

    @GET("/users")
    suspend fun getUserList(): List<UserListItemDto>

    @GET("/search/users")
    suspend fun searchUser(@Query("q") username: String): SearchUserResultDto

    @GET("/users/{username}")
    suspend fun getUserData(
        @Path("username") username: String
    ): UserDataDto

    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String
    ): List<UserListItemDto>

    @GET("/users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") userName: String
    ): List<UserListItemDto>
}