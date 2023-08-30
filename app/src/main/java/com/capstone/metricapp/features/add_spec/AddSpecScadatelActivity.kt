package com.capstone.metricapp.features.add_spec

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.databinding.ActivityAddSpecScadatelBinding
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class AddSpecScadatelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecScadatelBinding
    private val viewModel: AddSpecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecScadatelBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val id = intent.getStringExtra(KEY_ID_KEYPOINTS) ?: "bruh"

        viewModel.getUserToken().observe(this) { token ->
            Log.e("TEST 123", token.toString())
            setupHeaderInfo(token, id)

            setupButtonAdd(token, id)
        }


    }

    private fun setupButtonAdd(token: String, id: String) {
        binding.btnSave.setOnClickListener {
            val edMerk = binding.edScadatelMerk.text.toString()
            val edType = binding.edScadatelType.text.toString()
            val edMainVolt = binding.edScadatelMainVolt.text.toString()
            val edBackupVolt = binding.edScadatelBackupVolt.text.toString()
            val edOS = binding.edScadatelOs.text.toString()
            val edDate = binding.edScadatelDate.text.toString()
            val edNote = binding.edNotes.text.toString()
            
            Log.e("TEST", edMerk)
            viewModel.updateSpecScadatel(
                token,
                id,
                edMerk,
                edType,
                edMainVolt,
                edBackupVolt,
                edOS,
                edDate
            ).observe(this) { scadatel ->
                when (scadatel) {
                    is Resource.Error -> {
                        showLoading(false)
                        buttonEnabled(true)
                        showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                    }
                    is Resource.Loading -> {
                        showLoading(true)
                        buttonEnabled(false)
                    }
                    is Resource.Message -> {
                        Log.e("AddSpecScadatelActivity", scadatel.message.toString())
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        buttonEnabled(true)
                        showLongToast("Spesifikasi keypoint berhasil diperbaharui")

                        val backToHome = Intent(this, HomeActivity::class.java)
                        backToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(backToHome)
                        finish()
                    }
                }
            }
        }
    }

    private fun setupHeaderInfo(token: String, id: String) {
        viewModel.getScadatelById(token, id).observe(this) { scadatel ->
            when (scadatel) {
                is Resource.Error -> {
                    showLoading(false)
                    showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                }
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Message -> {
                    Log.e("AddSpecScadatelActivity", scadatel.message.toString())
                }
                is Resource.Success -> {
                    showLoading(false)

                    binding.tvAddSpecRegion.text = scadatel.data?.region
                    binding.tvAddSpecKeypoint.text = scadatel.data?.keypoint
                    binding.tvAddSpecDate.text =
                        DateUtil.convertDateKeypoints(scadatel.data?.dateCreated!!)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonEnabled(isEnabled: Boolean) {
        binding.btnSave.isEnabled = isEnabled
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}