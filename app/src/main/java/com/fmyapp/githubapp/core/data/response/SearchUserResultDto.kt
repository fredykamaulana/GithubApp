package com.fmyapp.githubapp.core.data.response

import com.squareup.moshi.Json

data class SearchUserResultDto(
    @field:Json(name = "items")
    val result: List<UserListItemDto>? = null
)
