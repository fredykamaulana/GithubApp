package com.fmyapp.githubapp.core.data.localsource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_github_user")
data class FavouriteUserEntity(

    @ColumnInfo(name = "login")
    val login: String? = null,

    @ColumnInfo(name = "company")
    val company: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "public_repos")
    val publicRepos: Int? = null,

    @ColumnInfo(name = "followers")
    val followers: Int? = null,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "following")
    val following: Int? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "location")
    val location: String? = null,

    @ColumnInfo(name = "favourite")
    val favourite: Boolean? = null
)
