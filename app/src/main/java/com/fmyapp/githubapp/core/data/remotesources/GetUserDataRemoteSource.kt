package com.fmyapp.githubapp.core.data.remotesources

import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.services.GithubUserService
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.data.utils.SafeApiCall
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetUserDataRemoteSource @Inject constructor(
    private val githubUserService: GithubUserService
) : SafeApiCall() {
    fun getUserData(username: String): Flow<Result<UserDataDto>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { githubUserService.getUserData(username = username) })
        }
    }
}