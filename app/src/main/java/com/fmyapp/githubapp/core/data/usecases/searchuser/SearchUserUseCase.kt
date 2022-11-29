package com.fmyapp.githubapp.core.data.usecases.searchuser

import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface SearchUserUseCase {
    suspend fun searchUser(username: String): Flow<Result<SearchUserResultDto>>
}