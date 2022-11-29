package com.fmyapp.githubapp.core.data.usecases.getuserdetail

import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserDataUseCase {
    suspend fun getUserData(username: String): Flow<Result<UserDataDto>>
}