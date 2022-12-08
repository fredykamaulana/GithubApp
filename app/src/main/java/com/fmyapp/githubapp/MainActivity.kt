package com.fmyapp.githubapp

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.fmyapp.githubapp.core.utils.loadRectangleImageWithUrl
import com.fmyapp.githubapp.core.utils.setupToolbar
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.ActivityMainBinding
import com.fmyapp.githubapp.setting.SettingViewModel
import com.fmyapp.githubapp.userdetail.UserDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserDetailFragment.FragmentCallBack {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private val vm: SettingViewModel by viewModels()

    private val navController by lazy {
        Navigation.findNavController(this, R.id.navHostFragment)
    }

    private val appBarConfiguration = AppBarConfiguration(
        setOf(R.id.splashFragment, R.id.userListFragment, R.id.userDetailFragment)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeThemeSetting()
        setupOnBackPressed()
        setupNavigation()
    }

    private fun observeThemeSetting() {
        vm.getThemeSettings().observe(this) { isDarkMode ->
            val themeMode =
                if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(themeMode)
        }
    }

    private fun setupNavigation() {
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener(navigationListener)
    }

    private val navigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    setWindowToFullScreen()
                }
                R.id.userListFragment -> {
                    setWindowWithToolbar()
                    setToolbarNotExpanded()
                }
                R.id.userDetailFragment -> {
                    setWindowWithToolbar()
                    setToolbarCanExpanded()
                }
                R.id.favouriteUserFragment -> {
                    setWindowWithToolbar()
                    setToolbarNotExpanded()
                }
                R.id.settingFragment -> {
                    setWindowWithToolbar()
                    setToolbarNotExpanded()
                }
            }
        }

    private fun setWindowToFullScreen() {
        binding.appBarLayout.visibility = View.GONE
    }

    private fun setWindowWithToolbar(title: String = "Github App") {
        binding.appBarLayout.visibility = View.VISIBLE
        setupToolbar(binding.toolbar, title)
    }

    private fun setToolbarCanExpanded() {
        binding.appBarLayout.setExpanded(true)
        binding.nestedScrollLayout.isNestedScrollingEnabled = true
    }

    private fun setToolbarNotExpanded() {
        binding.appBarLayout.setExpanded(false)
        binding.nestedScrollLayout.isNestedScrollingEnabled = false
    }

    private fun setupOnBackPressed() {
        val onBackPressed = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentFragment = navController.currentDestination
                when (currentFragment?.id) {
                    R.id.userListFragment -> finish()
                    else -> navController.navigateUp()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressed)
    }

    override fun onFragmentCallBack(imgUrl: String) {
        binding.ivToolbar.loadRectangleImageWithUrl(this, imgUrl)
    }
}