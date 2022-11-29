package com.fmyapp.githubapp.core.data.repositories.getfollowing

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserFollowingRepository {
    suspend fun getUserFollowing(username: String): Flow<Result<List<UserListItemDto>>>
}