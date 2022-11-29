package com.fmyapp.githubapp.core.data.repositories.getuserdata

import com.fmyapp.githubapp.core.data.remotesources.GetUserDataRemoteSource
import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserDataRepositoryImpl @Inject constructor(
    private val getUserDataRemoteSource: GetUserDataRemoteSource
) : GetUserDataRepository {
    override suspend fun getUserData(username: String): Flow<Result<UserDataDto>> {
        return getUserDataRemoteSource.getUserData(username)
    }
}