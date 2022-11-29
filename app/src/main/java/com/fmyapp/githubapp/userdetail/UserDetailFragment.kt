package com.fmyapp.githubapp.userdetail

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.data.response.UserDataDto
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.utils.showSnackBar
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.FragmentUserDetailBinding
import com.fmyapp.githubapp.userdetail.adapter.UserDetailViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val binding: FragmentUserDetailBinding by viewBinding(FragmentUserDetailBinding::bind)
    private val vm: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()
    private val username: String by lazy { args.username }
    private val userDetailViewPagerAdapter by lazy {
        UserDetailViewPagerAdapter(
            requireActivity(),
            username
        )
    }

    private var fragmentCallBack: FragmentCallBack? = null
    private var isSavedInstanceStateNull = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentCallBack = requireActivity() as FragmentCallBack
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isSavedInstanceStateNull = savedInstanceState === null
        setupObserver()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        if (isSavedInstanceStateNull) vm.getUserData(username)
    }

    private fun setupObserver() {
        vm.userDataResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserDetail.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbUserDetail.visibility = View.GONE
                    bindDataToView(it.data)
                }
                is Result.Empty -> {
                    binding.pbUserDetail.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.pbUserDetail.visibility = View.GONE
                    showSnackBar(binding.root, it.errorMessage ?: "Unknown Error") {}
                }
            }
        }
    }

    private fun bindDataToView(data: UserDataDto) {
        fragmentCallBack?.onFragmentCallBack(data.avatarUrl ?: "")

        binding.tvUserName.text = data.login
        binding.tvUserFullName.text = data.name
        binding.tvUserCompany.text =
            getString(R.string.company_string_format, data.company ?: "-")
        binding.tvUserLocation.text =
            getString(R.string.location_string_format, data.location ?: "-")
        binding.tvUserRepo.text = getString(R.string.repo_string_format, data.publicRepos)
        binding.tvUserFollowers.text = getString(R.string.followers_string_format, data.followers)
        binding.tvUserFollowing.text = getString(R.string.following_string_format, data.following)
    }

    private fun setupView() {
        binding.userNetworkViewPager.adapter = userDetailViewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.userNetworkViewPager) { tab, pos ->
            tab.text = TAB_TITLES[pos]
        }.attach()
    }

    interface FragmentCallBack {
        fun onFragmentCallBack(imgUrl: String)
    }

    companion object {
        private val TAB_TITLES = listOf("Followers", "Following")
    }
}