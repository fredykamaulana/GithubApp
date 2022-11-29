package com.fmyapp.githubapp.favouriteuser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.utils.ItemClickListener
import com.fmyapp.githubapp.core.utils.showPopUpMenu
import com.fmyapp.githubapp.core.utils.showSnackBar
import com.fmyapp.githubapp.core.utils.showToast
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.FragmentFavouriteUserBinding
import com.fmyapp.githubapp.favouriteuser.adapter.FavouriteUserAdapter
import com.fmyapp.githubapp.userlist.UserListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteUserFragment : Fragment(R.layout.fragment_favourite_user), ItemClickListener {

    private val binding: FragmentFavouriteUserBinding by viewBinding(FragmentFavouriteUserBinding::bind)
    private val vm: FavouriteUserViewModel by viewModels()
    private val adapter: FavouriteUserAdapter by lazy { FavouriteUserAdapter(this) }

    private var isSavedInstanceStateNull = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isSavedInstanceStateNull = savedInstanceState === null
        setupObserver()
        setupView()

    }

    override fun onResume() {
        super.onResume()
        if (isSavedInstanceStateNull) vm.getAllFavouriteUser()
    }

    private fun setupObserver() {
        vm.getAllFavouriteUser.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserList.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbUserList.visibility = View.GONE
                    adapter.submitList(it.data)
                }
                is Result.Empty -> {
                    binding.pbUserList.visibility = View.GONE
                    binding.tvNoDataFound.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    binding.pbUserList.visibility = View.GONE
                    showSnackBar(binding.root, it.errorMessage ?: "") {}
                }
            }
        }

        vm.deleteFavouriteUser.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserList.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbUserList.visibility = View.GONE
                    showToast("User berhasil dihapus dari daftar favourite")
                    vm.getAllFavouriteUser()
                }
                is Result.Empty -> {
                    binding.pbUserList.visibility = View.GONE
                    showToast("User ini tidak terhapus dari daftar favourite")
                }
                is Result.Error -> {
                    binding.pbUserList.visibility = View.GONE
                    showToast("User ini tidak terhapus dari daftar favourite")
                }
            }
        }
    }

    private fun setupView() {
        binding.rvUserList.adapter = adapter
    }

    override fun onItemClick(data: String) {
        findNavController().navigate(
            UserListFragmentDirections.toUserDetailFragment().setUsername(data)
        )
    }

    override fun onItemMenuClick(view: View, user: FavouriteUserEntity) {
        showPopUpMenu(view, listOf("Hapus Favorite")) {
            vm.deleteFavouriteUser(user = user)
        }
    }
}