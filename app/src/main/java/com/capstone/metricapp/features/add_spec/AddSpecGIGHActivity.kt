package com.capstone.metricapp.features.add_spec

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.databinding.ActivityAddSpecGighBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSpecGIGHActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecGighBinding
    private val viewModel: AddSpecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecGighBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}