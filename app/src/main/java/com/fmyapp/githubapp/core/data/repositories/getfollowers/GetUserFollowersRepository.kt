package com.fmyapp.githubapp.core.data.repositories.getfollowers

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowersRepository {
    suspend fun getUserFollowers(username: String): Flow<Result<List<UserListItemDto>>>
}