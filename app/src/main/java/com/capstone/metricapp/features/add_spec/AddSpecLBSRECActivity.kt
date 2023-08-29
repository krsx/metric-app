package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecLbsrecBinding

class AddSpecLBSRECActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecLbsrecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKeypointsSpecLbsrecBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}