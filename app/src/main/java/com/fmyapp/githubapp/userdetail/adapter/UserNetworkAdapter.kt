package com.fmyapp.githubapp.userdetail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fmyapp.githubapp.core.data.response.UserListItemDto
import com.fmyapp.githubapp.core.utils.loadCircleImageWithUrl
import com.fmyapp.githubapp.databinding.ItemLayoutUserListBinding

class UserNetworkAdapter :
    ListAdapter<UserListItemDto, UserNetworkAdapter.UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder.create(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class UserViewHolder(private val binding: ItemLayoutUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(userData: UserListItemDto) {
            binding.ivUserImage.loadCircleImageWithUrl(
                binding.root.context,
                userData.avatarUrl ?: ""
            )
            binding.apply {
                tvUserName.text = userData.login
                tvUserRepo.visibility = View.GONE
                tvUserFollower.visibility = View.GONE
            }
        }

        companion object {
            fun create(parent: ViewGroup): UserViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutUserListBinding.inflate(inflater, parent, false)
                return UserViewHolder(binding)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UserListItemDto>() {
            override fun areItemsTheSame(
                oldItem: UserListItemDto,
                newItem: UserListItemDto
            ): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: UserListItemDto, newItem: UserListItemDto)
                    : Boolean = oldItem.id == newItem.id
        }
    }
}