package com.fmyapp.githubapp.setting

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fmyapp.githubapp.R
import com.fmyapp.githubapp.core.utils.viewBinding
import com.fmyapp.githubapp.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val binding: FragmentSettingBinding by viewBinding(FragmentSettingBinding::bind)
    private val vm: SettingViewModel by activityViewModels()

    private var isSavedInstanceStateNull = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isSavedInstanceStateNull = savedInstanceState === null
        setupView()
        observeThemeSetting()
    }

    override fun onResume() {
        super.onResume()
        if (isSavedInstanceStateNull) vm.getThemeSettings()
    }

    private fun setupView() {
        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            vm.saveThemeSetting(isChecked)
        }
    }

    private fun observeThemeSetting() {
        vm.getThemeSettings().observe(viewLifecycleOwner) { isDarkMode ->
            binding.switchTheme.isChecked = isDarkMode
        }
    }
}