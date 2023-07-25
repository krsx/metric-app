package com.capstone.metricapp.features.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.metricapp.R
import com.capstone.metricapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}