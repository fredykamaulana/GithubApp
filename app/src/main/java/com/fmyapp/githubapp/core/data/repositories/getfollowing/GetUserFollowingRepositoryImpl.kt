package com.fmyapp.githubapp.core.data.repositories.getfollowing

import com.fmyapp.githubapp.core.data.remotesources.GetUserFollowingRemoteSource
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserFollowingRepositoryImpl @Inject constructor(
    private val getUserFollowingRemoteSource: GetUserFollowingRemoteSource
) : GetUserFollowingRepository {
    override suspend fun getUserFollowing(username: String): Flow<Result<List<UserListItemDto>>> {
        return getUserFollowingRemoteSource.getUserFollowing(username = username)
    }
}