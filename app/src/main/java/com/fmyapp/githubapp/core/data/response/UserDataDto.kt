package com.fmyapp.githubapp.core.data.response

import com.squareup.moshi.Json

data class UserDataDto(

	@field:Json(name="login")
	val login: String? = null,

	@field:Json(name="company")
	val company: String? = null,

	@field:Json(name="id")
	val id: Int? = null,

	@field:Json(name="public_repos")
	val publicRepos: Int? = null,

	@field:Json(name="followers")
	val followers: Int? = null,

	@field:Json(name="avatar_url")
	val avatarUrl: String? = null,

	@field:Json(name="following")
	val following: Int? = null,

	@field:Json(name="name")
	val name: String? = null,

	@field:Json(name="location")
	val location: String? = null
)
