package com.fmyapp.githubapp.core.data.remotesources

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.services.GithubUserService
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.data.utils.SafeApiCall
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetUserFollowingRemoteSource @Inject constructor(
    private val githubUserService: GithubUserService
) : SafeApiCall() {
    fun getUserFollowing(username: String): Flow<Result<List<UserListItemDto>>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { githubUserService.getUserFollowing(username) })
        }
    }
}