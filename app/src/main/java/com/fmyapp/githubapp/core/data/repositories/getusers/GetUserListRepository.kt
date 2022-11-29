package com.fmyapp.githubapp.core.data.repositories.getusers

import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import kotlinx.coroutines.flow.Flow

interface GetUserListRepository {
    suspend fun getUserList(): Flow<Result<List<UserListItemDto>>>
}