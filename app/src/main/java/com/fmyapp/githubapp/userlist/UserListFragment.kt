package com.fmyapp.githubapp.userlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.data.localsource.entities.FavouriteUserEntity
import com.fmyapp.githubapp.core.data.utils.Result
import com.fmyapp.githubapp.core.utils.ItemClickListener
import com.fmyapp.githubapp.core.utils.hideKeyboard
import com.fmyapp.githubapp.core.utils.showPopUpMenu
import com.fmyapp.githubapp.core.utils.showSnackBar
import com.fmyapp.githubapp.core.utils.showToast
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.FragmentUserListBinding
import com.fmyapp.githubapp.userlist.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list), ItemClickListener {

    private val binding: FragmentUserListBinding by viewBinding(FragmentUserListBinding::bind)
    private val vm: UserListViewModel by viewModels()
    private val adapter: UserListAdapter by lazy { UserListAdapter(this) }

    private var isSavedInstanceStateNull = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isSavedInstanceStateNull = savedInstanceState === null
        setupObserver()
        setupView()
        setupMenu()
    }

    override fun onResume() {
        super.onResume()
        if (isSavedInstanceStateNull) {
            vm.setSearchUserResultEmpty()
            vm.getUserList()
        }

        binding.searchUserList.setQuery("", false)
    }

    private fun setupObserver() {
        vm.userListResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserList.visibility = View.VISIBLE
                    binding.rvUserList.visibility = View.GONE
                    binding.tvNoDataFound.visibility = View.GONE
                }
                is Result.Success -> {
                    adapter.submitList(it.data)
                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.VISIBLE
                }
                is Result.Empty -> {
                    adapter.submitList(listOf())
                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.GONE
                    binding.tvNoDataFound.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.VISIBLE
                    showSnackBar(binding.root, it.errorMessage ?: "Unknown Error") {}
                }
            }
        }

        vm.searchUserResult.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserList.visibility = View.VISIBLE
                    binding.rvUserList.visibility = View.GONE
                    binding.tvNoDataFound.visibility = View.GONE
                }
                is Result.Success -> {
                    if (it.data.result?.isEmpty() == true) binding.tvNoDataFound.visibility =
                        View.VISIBLE
                    else {
                        binding.tvNoDataFound.visibility = View.GONE
                        adapter.submitList(it.data.result)
                    }

                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.VISIBLE
                }
                is Result.Empty -> {
                    adapter.submitList(listOf())
                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.pbUserList.visibility = View.GONE
                    binding.rvUserList.visibility = View.VISIBLE
                    showSnackBar(binding.root, it.errorMessage ?: "Unknown Error") {}
                }
            }
        }

        vm.setUserAsFavourite.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    binding.pbUserList.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.pbUserList.visibility = View.GONE
                    if (it.data == -1L) {
                        showToast("User already in favourite")
                    } else showToast("User added as favourite")

                    vm.setUserFavouriteResultAsNeutral()
                }
                is Result.Empty -> {
                    binding.pbUserList.visibility = View.GONE
                }
                is Result.Error -> {
                    binding.pbUserList.visibility = View.GONE
                    showToast("User already in favourite")
                }
            }
        }
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            hideKeyboard()
            vm.setUserListResultEmpty()
            vm.searchUser(query?.trim() ?: "")
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText?.isEmpty() == true) {
                vm.setSearchUserResultEmpty()
                vm.getUserList()
            } else {
                vm.setUserListResultEmpty()
                vm.searchUser(newText?.trim() ?: "")
            }
            return true
        }
    }

    private fun setupView() {
        binding.rvUserList.adapter = adapter
        binding.rvUserList.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        binding.searchUserList.setOnQueryTextListener(queryTextListener)
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.setting -> {
                        findNavController().navigate(
                            UserListFragmentDirections.toSettingFragment()
                        )
                    }
                    R.id.favourite -> {
                        findNavController().navigate(
                            UserListFragmentDirections.toFavouriteUserFragment()
                        )
                    }
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onItemClick(data: String) {
        findNavController().navigate(
            UserListFragmentDirections.toUserDetailFragment().setUsername(data)
        )
    }

    override fun onItemMenuClick(view: View, user: FavouriteUserEntity) {
        showPopUpMenu(view, listOf("Set as favorite")) {
            vm.setUserAsFavourite(user = user)
        }
    }
}