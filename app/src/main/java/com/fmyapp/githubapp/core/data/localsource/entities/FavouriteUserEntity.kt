package com.fmyapp.githubapp.core.data.localsource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_github_user")
data class FavouriteUserEntity(

    @ColumnInfo(name = "login")
    val login: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "favourite")
    val favourite: Boolean? = null
)
