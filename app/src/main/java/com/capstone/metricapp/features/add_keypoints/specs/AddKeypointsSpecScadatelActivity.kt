package com.capstone.metricapp.features.add_keypoints.specs

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.createIdRandomizer
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecScadatelBinding
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class AddKeypointsSpecScadatelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecScadatelBinding
    private val viewModel: AddKeypointsSpecViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsSpecScadatelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            setupButtonSave()
        }
    }

    private fun setupButtonSave() {
        val scadatelMerk = binding.edScadatelMerk.text.toString()
        val scadatelType = binding.edScadatelType.text.toString()
        val scadatelMainVolt = binding.edScadatelMainVolt.text.toString()
        val scadatelBackupVolt = binding.edScadatelBackupVolt.text.toString()
        val scadatelOs = binding.edScadatelOs.text.toString()
        val scadatelDate = binding.edScadatelDate.text.toString()
        val scadatelNotes = binding.edNotes.text.toString()

        if (scadatelMerk.isNotEmpty() && scadatelType.isNotEmpty() && scadatelMainVolt.isNotEmpty() && scadatelBackupVolt.isNotEmpty() && scadatelOs.isNotEmpty() && scadatelDate.isNotEmpty() && scadatelNotes.isNotEmpty()) {
            viewModel.getUserToken().observe(this) { token ->
                val keypointType = intent.getStringExtra(KEY_KEYPOINT_TYPE)
                val region = intent.getStringExtra(KEY_REGION)
                val keypoint = intent.getStringExtra(KEY_KEYPOINT)

                val id = createIdRandomizer(keypointType!!)
                viewModel.createScadatelKeypoint(
                    token,
                    id,
                    keypoint!!,
                    region!!,
                    scadatelMerk,
                    scadatelType,
                    scadatelMainVolt,
                    scadatelBackupVolt,
                    scadatelOs,
                    scadatelDate,
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
                            Log.e("AddKeypointsSpecScadatelActivity", scadatel.message.toString())
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            buttonEnabled(true)
                            showToast("Data keypoints baru telah tersimpan")

                            val intentToHome = Intent(this, HomeActivity::class.java)
                            startActivity(intentToHome)
                            finish()
                        }
                    }
                }
            }
        } else {
            showToast("Tolong isi semua data")
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun buttonEnabled(isEnabled: Boolean) {
        binding.btnSave.isEnabled = isEnabled
    }

    companion object {
        private const val KEY_KEYPOINT = "key_keypoint"
        private const val KEY_KEYPOINT_TYPE = "key_keypoint_type"
        private const val KEY_REGION = "key_region"
    }
}