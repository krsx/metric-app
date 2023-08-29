package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecScadatelBinding

class AddSpecScadatelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecScadatelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKeypointsSpecScadatelBinding.inflate(layoutInflater)

        setContentView(binding.root)

        
    }
}