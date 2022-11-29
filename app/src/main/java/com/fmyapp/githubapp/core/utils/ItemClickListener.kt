package com.fmyapp.githubapp.core.utils

import android.view.View
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity

interface ItemClickListener {
    fun onItemClick(data: String)
    fun onItemMenuClick(view: View, user: FavouriteUserEntity)
}