package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddSpecGighBinding

class AddSpecGIGHActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecGighBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecGighBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}