package com.fmyapp.githubapp.core.data.repositories.getfollowers

import com.fmyapp.githubapp.core.data.remotesources.GetUserFollowersRemoteSource
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserFollowersRepositoryImpl @Inject constructor(
    private val getUserFollowersRemoteSource: GetUserFollowersRemoteSource
) : GetUserFollowersRepository {
    override suspend fun getUserFollowers(username: String): Flow<Result<List<UserListItemDto>>> {
        return getUserFollowersRemoteSource.getUserFollowers(username = username)
    }
}