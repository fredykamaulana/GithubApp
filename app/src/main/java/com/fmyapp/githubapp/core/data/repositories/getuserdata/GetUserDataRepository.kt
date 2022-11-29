package com.fmyapp.githubapp.core.data.repositories.getuserdata

import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserDataRepository {
    suspend fun getUserData(username: String): Flow<Result<UserDataDto>>
}