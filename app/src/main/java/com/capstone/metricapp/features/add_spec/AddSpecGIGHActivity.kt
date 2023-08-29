package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecGighBinding

class AddSpecGIGHActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecGighBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKeypointsSpecGighBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}