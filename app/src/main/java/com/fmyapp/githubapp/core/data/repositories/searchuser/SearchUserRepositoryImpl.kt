package com.fmyapp.githubapp.core.data.repositories.searchuser

import com.fmyapp.githubapp.core.data.remotesources.SearchUserRemoteSource
import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchUserRepositoryImpl @Inject constructor(
    private val searchUserRemoteSource: SearchUserRemoteSource
) : SearchUserRepository {
    override suspend fun searchUser(username: String): Flow<Result<SearchUserResultDto>> {
        return searchUserRemoteSource.searchUser(username = username)
    }
}