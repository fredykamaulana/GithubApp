package com.fmyapp.githubapp.core.data.repositories.getusers

import com.fmyapp.githubapp.core.data.remotesources.GetUserListRemoteSource
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserListRepositoryImpl @Inject constructor(
    private val getUserListRemoteSource: GetUserListRemoteSource
) : GetUserListRepository {
    override suspend fun getUserList(): Flow<Result<List<UserListItemDto>>> {
        return getUserListRemoteSource.getUserList()
    }
}