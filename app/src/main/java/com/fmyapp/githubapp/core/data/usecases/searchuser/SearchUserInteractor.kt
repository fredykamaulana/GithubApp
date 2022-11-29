package com.fmyapp.githubapp.core.data.usecases.searchuser

import com.fmyapp.githubapp.core.data.repositories.searchuser.SearchUserRepository
import com.fmyapp.githubapp.core.data.response.SearchUserResultDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchUserInteractor @Inject constructor(
    private val repository: SearchUserRepository
) : SearchUserUseCase {
    override suspend fun searchUser(username: String): Flow<Result<SearchUserResultDto>> {
        return repository.searchUser(username = username)
    }
}