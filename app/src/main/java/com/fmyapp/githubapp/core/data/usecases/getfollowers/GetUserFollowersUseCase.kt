package com.fmyapp.githubapp.core.data.usecases.getfollowers

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowersUseCase {
    suspend fun getUserFollowers(username: String): Flow<Result<List<UserListItemDto>>>
}