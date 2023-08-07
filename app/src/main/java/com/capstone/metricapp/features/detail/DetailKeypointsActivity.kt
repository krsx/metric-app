package com.capstone.metricapp.features.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.metricapp.R
import com.capstone.metricapp.databinding.ActivityDetailKeypointsBinding

class DetailKeypointsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKeypointsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKeypointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}