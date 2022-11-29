package com.fmyapp.githubapp.core.data.usecases.getfollowers

import com.fmyapp.githubapp.core.data.repositories.getfollowers.GetUserFollowersRepository
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserFollowersInteractor @Inject constructor(
    private val repository: GetUserFollowersRepository
) : GetUserFollowersUseCase {
    override suspend fun getUserFollowers(username: String): Flow<Result<List<UserListItemDto>>> {
        return repository.getUserFollowers(username = username)
    }
}