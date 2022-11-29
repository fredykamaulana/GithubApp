package com.fmyapp.githubapp.core.data.usecases.getfollowing

import com.fmyapp.githubapp.core.data.repositories.getfollowing.GetUserFollowingRepository
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.data.utils.Result
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserFollowingInteractor @Inject constructor(
    private val repository: GetUserFollowingRepository
) : GetUserFollowingUseCase {
    override suspend fun getUserFollowing(username: String): Flow<Result<List<UserListItemDto>>> {
        return repository.getUserFollowing(username = username)
    }
}