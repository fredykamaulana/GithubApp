package com.fmyapp.githubapp.core.data.usecases.getuserdetail

import com.fmyapp.githubapp.core.data.repositories.getuserdata.GetUserDataRepository
import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserDataInteractor @Inject constructor(private val repository: GetUserDataRepository) :
    GetUserDataUseCase {
    override suspend fun getUserData(username: String): Flow<Result<UserDataDto>> {
        return repository.getUserData(username)
    }
}