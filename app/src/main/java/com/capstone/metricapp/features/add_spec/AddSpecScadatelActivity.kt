package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddSpecScadatelBinding

class AddSpecScadatelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecScadatelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecScadatelBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }
}