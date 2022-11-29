package com.fmyapp.githubapp.core.data.remotesources

import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.services.GithubUserService
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.data.utils.SafeApiCall
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class SearchUserRemoteSource @Inject constructor(
    private val githubUserService: GithubUserService
) : SafeApiCall() {
    fun searchUser(username: String): Flow<Result<SearchUserResultDto>> {
        return flow {
            emit(Result.Loading)
            emitAll(safeApiCall { githubUserService.searchUser(username = username) })
        }
    }
}