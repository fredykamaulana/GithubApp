package com.fmyapp.githubapp.favouriteuser.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.utils.ItemClickListener
import com.fmyapp.githubapp.core.utils.loadCircleImageWithUrl
import com.fmyapp.githubapp.databinding.ItemLayoutUserListBinding

class FavouriteUserAdapter(private val listener: ItemClickListener) :
    ListAdapter<FavouriteUserEntity, FavouriteUserAdapter.UserViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder.create(parent = parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(userData = getItem(position), listener = listener)
    }

    class UserViewHolder(private val binding: ItemLayoutUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(userData: FavouriteUserEntity, listener: ItemClickListener) {
            binding.root.setOnClickListener { listener.onItemClick(userData.login ?: "") }
            binding.ivUserImage.loadCircleImageWithUrl(
                binding.root.context,
                userData.avatarUrl ?: ""
            )
            binding.tvUserName.text = userData.login
            binding.tvUserRepo.text = binding.root.context.getString(R.string.click_label)
            binding.tvUserFollower.text = binding.root.context.getString(R.string.more_detail_label)

            binding.ivMenuImage.setOnClickListener { listener.onItemMenuClick(it,FavouriteUserEntity(

            )) }
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
        val diffUtil = object : DiffUtil.ItemCallback<FavouriteUserEntity>() {
            override fun areItemsTheSame(
                oldItem: FavouriteUserEntity,
                newItem: FavouriteUserEntity
            ): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(
                oldItem: FavouriteUserEntity,
                newItem: FavouriteUserEntity
            )
                    : Boolean = oldItem.id == newItem.id
        }
    }
}