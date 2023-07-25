package com.capstone.metricapp.features.scan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.metricapp.R
import com.capstone.metricapp.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}