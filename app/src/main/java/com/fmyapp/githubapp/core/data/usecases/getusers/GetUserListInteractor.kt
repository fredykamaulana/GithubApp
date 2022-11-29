package com.fmyapp.githubapp.core.data.usecases.getusers

import com.fmyapp.githubapp.core.data.repositories.getusers.GetUserListRepository
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserListInteractor @Inject constructor(private val repository: GetUserListRepository) :
    GetUserListUseCase {
    override suspend fun getUserList(): Flow<Result<List<UserListItemDto>>> {
        return repository.getUserList()
    }
}