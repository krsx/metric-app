package com.capstone.metricapp.features.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.constans.Divisions
import com.capstone.metricapp.databinding.ActivityDetailKeypointsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailKeypointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKeypointsBinding
    private val viewModel: DetailKeypointViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKeypointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        viewModel.getUserToken().observe(this) { token ->
            viewModel.getUserDivision().observe(this) { division ->
                val keypointsId = (intent.getStringExtra(KEY_ID_KEYPOINTS)!!)
                setupHeaderInfo(token, keypointsId, division)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupHeaderInfo(token: String, id: String, division: String) {
        when (division) {
            Divisions.RTU.divisionName -> {
                viewModel.getRTUById(token, id).observe(this) { rtu ->
                    when (rtu) {
                        is Resource.Error -> {
                            Log.e("TEST", "ERROR")
                        }
                        is Resource.Loading -> {
                            Log.e("TEST", "LOADING")
                        }
                        is Resource.Message -> {
                            Log.e("TEST", "MESSAGE")

                        }
                        is Resource.Success -> {
                            Log.e("TEST", "SUCCESS")

                            binding.apply {
                                tvDetailRegion.text = rtu.data?.region
                                tvDetailKeypoint.text = rtu.data?.keypoint
                                tvDetailDate.text =
                                    DateUtil.convertDateKeypoints(rtu.data!!.dateCreated)
                            }
                        }
                    }
                }
            }
            Divisions.SCADATEL.divisionName -> {
                viewModel.getScadatelById(token, id).observe(this) { scadatel ->
                    when (scadatel) {
                        is Resource.Error -> {
                            Log.e("TEST", "ERROR")
                        }
                        is Resource.Loading -> {
                            Log.e("TEST", "LOADING")
                        }
                        is Resource.Message -> {
                            Log.e("TEST", "MESSAGE")
                        }
                        is Resource.Success -> {
                            Log.e("TEST", "SUCCESS")

                            binding.apply {
                                tvDetailRegion.text = scadatel.data?.region
                                tvDetailKeypoint.text = scadatel.data?.keypoint
                                tvDetailDate.text =
                                    DateUtil.convertDateKeypoints(scadatel.data!!.dateCreated)
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}