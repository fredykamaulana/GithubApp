package com.fmyapp.githubapp.core.data.usecases.getusers

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import kotlinx.coroutines.flow.Flow
import com.fmyapp.githubapp.core.data.utils.Result

interface GetUserListUseCase {
    suspend fun getUserList(): Flow<Result<List<UserListItemDto>>>
}