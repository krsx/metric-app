package com.capstone.metricapp.features.settings

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val viewModel: SettingsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            val logoutDialog = LogoutDialog()
            logoutDialog.show(supportFragmentManager, "Logout Dialog")
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        viewModel.getUserDivision().observe(this) {
            binding.tvDivisionUser.text = it.uppercase()
        }

        viewModel.getUserEmail().observe(this) {
            binding.tvEmailUser.text = it
        }
    }
}