package com.capstone.metricapp.features.detail

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.databinding.ActivityDetailKeypointsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailKeypointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKeypointsBinding
    private val detailViewModel: DetailKeypointViewModel by viewModels()

    private var keypointsType: KeypointsType = KeypointsType.SCADATEL

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKeypointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

//        setupHeaderInfo(intent.getStringExtra(KEY_ID_SCADATEL)!!)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun setupHeaderInfo(id: String) {
//        detailViewModel.getScadatelById(id).observe(this) { scadatel ->
//            when (scadatel) {
//                is Resource.Error -> {
//
//                }
//                is Resource.Loading -> {
//
//                }
//                is Resource.Message -> {
//
//                }
//                is Resource.Success -> {
//                    binding.apply {
//                        tvDetailRegion.text = scadatel.data?.region
//                        tvDetailKeypoint.text = scadatel.data?.keypoint
//                        tvDetailDate.text =
//                            DateUtil.convertDateKeypoints(scadatel.data!!.dateCreated)
//                    }
//                }
//            }
//        }
//    }

    companion object {
        const val KEY_ID_SCADATEL = "key_id_scadatel"
    }
}