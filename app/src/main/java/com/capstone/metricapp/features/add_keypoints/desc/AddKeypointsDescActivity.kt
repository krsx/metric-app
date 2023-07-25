package com.capstone.metricapp.features.add_keypoints.desc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.metricapp.R
import com.capstone.metricapp.databinding.ActivityAddKeypointsDescBinding

class AddKeypointsDescActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddKeypointsDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}