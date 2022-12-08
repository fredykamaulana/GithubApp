package com.fmyapp.githubapp.userdetail.usernetwork

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.utils.showSnackBar
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.FragmentUserNetworkBinding
import com.fmyapp.githubapp.userdetail.adapter.UserNetworkAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserNetworkFragment : Fragment(R.layout.fragment_user_network) {

    private val binding: FragmentUserNetworkBinding by viewBinding(FragmentUserNetworkBinding::bind)
    private val vm: UserNetworkViewModel by viewModels()
    private val tabSection by lazy { arguments?.getInt(TAB_SECTION, 0) ?: 0 }
    private val username by lazy { arguments?.getString(USER_NAME_ARG, "") ?: "" }
    private val adapter by lazy { UserNetworkAdapter() }

    private var isSavedInstanceStateNull = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isSavedInstanceStateNull = savedInstanceState === null
        setupObserver()
        setupData()
        setupView()
    }

    private fun setupData() {
        when (tabSection) {
            0 -> if (isSavedInstanceStateNull) vm.getUserFollowers(username = username)
            1 -> if (isSavedInstanceStateNull) vm.getUserFollowing(username = username)
        }
    }

    private fun setupObserver() {
        vm.userFollowersResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserNetwork.visibility = View.VISIBLE
                }
                is Result.Empty -> {
                    binding.pbUserNetwork.visibility = View.GONE

                }
                is Result.Success -> {
                    binding.pbUserNetwork.visibility = View.GONE
                    if (it.data.isEmpty()) binding.tvNoDataFound.visibility = View.VISIBLE
                    else {
                        binding.tvNoDataFound.visibility = View.GONE
                        adapter.submitList(it.data)
                    }
                }
                is Result.Error -> {
                    binding.pbUserNetwork.visibility = View.GONE
                }
            }
        }
        vm.userFollowingResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserNetwork.visibility = View.VISIBLE
                    binding.tvNoDataFound.visibility = View.GONE
                }
                is Result.Empty -> {
                    binding.pbUserNetwork.visibility = View.GONE
                    binding.tvNoDataFound.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbUserNetwork.visibility = View.GONE
                    if (it.data.isEmpty()) binding.tvNoDataFound.visibility = View.VISIBLE
                    else {
                        binding.tvNoDataFound.visibility = View.GONE
                        adapter.submitList(it.data)
                    }
                }
                is Result.Error -> {
                    binding.pbUserNetwork.visibility = View.GONE
                    showSnackBar(binding.root, it.errorMessage ?: "Unknown Error") {}
                }
            }
        }
    }

    private fun setupView() {
        binding.rvUserNetworkList.adapter = adapter
        binding.rvUserNetworkList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    companion object {
        private const val USER_NAME_ARG = "username"
        private const val TAB_SECTION = "tab section"
    }
}