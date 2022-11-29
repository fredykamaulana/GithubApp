package com.fmyapp.githubapp.core.data.usecases.getfollowing

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowingUseCase {
    suspend fun getUserFollowing(username: String): Flow<Result<List<UserListItemDto>>>
}