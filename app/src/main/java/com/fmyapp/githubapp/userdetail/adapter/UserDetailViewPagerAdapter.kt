package com.fmyapp.githubapp.userdetail.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fmyapp.githubapp.userdetail.usernetwork.UserNetworkFragment

class UserDetailViewPagerAdapter(activity: FragmentActivity, private val username: String) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = UserNetworkFragment().apply {
        arguments = bundleOf(Pair(USER_NAME_ARG, username), Pair(TAB_SECTION, position))
    }

    companion object {
        private const val USER_NAME_ARG = "username"
        private const val TAB_SECTION = "tab section"
    }
}