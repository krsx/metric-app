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
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecLbsrecBinding
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)

class AddKeypointsSpecLBSRECActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecLbsrecBinding
    private val viewModel: AddKeypointsSpecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsSpecLbsrecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            setupButtonSave()
        }
    }

    private fun setupButtonSave() {
        val teleMerk = binding.edTeleMerk.text.toString()
        val teleType = binding.edTeleType.text.toString()
        val teleRange = binding.edTeleRange.text.toString()
        val teleSn = binding.edTeleSn.text.toString()
        val teleDate = binding.edTeleDate.text.toString()

        val simMainProvider = binding.edSimMainProvider.text.toString()
        val noMainProvider = binding.edNoSimCard.text.toString()
        val simBackupProvider = binding.edSimBackupProvider.text.toString()
        val noBackupProvider = binding.edNoSimCardBackup.text.toString()

        val rtuMerk = binding.edRtuMerk.text.toString()
        val rtuType = binding.edRtuType.text.toString()
        val rtuDate = binding.edRtuDate.text.toString()
        val rtuSn = binding.edRtuSn.text.toString()

        val batMerk = binding.edBatteryMerk.text.toString()
        val batType = binding.edBatteryType.text.toString()
        val batDate = binding.edBatteryDate.text.toString()

        val note = binding.edNotes.text.toString()

        if (teleMerk.isNotEmpty() && teleType.isNotEmpty() && teleRange.isNotEmpty() && teleSn.isNotEmpty() && teleDate.isNotEmpty()
            && simMainProvider.isNotEmpty() && noMainProvider.isNotEmpty() && simBackupProvider.isNotEmpty() && noBackupProvider.isNotEmpty()
            && rtuMerk.isNotEmpty() && rtuType.isNotEmpty() && rtuDate.isNotEmpty() && rtuSn.isNotEmpty()
            && batMerk.isNotEmpty() && batType.isNotEmpty() && batDate.isNotEmpty()
            && note.isNotEmpty()
        ) {
            viewModel.getUserToken().observe(this) { token ->
                val keypointType = intent.getStringExtra(KEY_KEYPOINT_TYPE)
                val region = intent.getStringExtra(KEY_REGION)
                val keypoint = intent.getStringExtra(KEY_KEYPOINT)

                val id = createIdRandomizer(keypointType!!)
                viewModel.createLBSKeypoint(
                    token,
                    id,
                    keypoint!!,
                    region!!,
                    teleMerk,
                    teleType,
                    teleRange,
                    teleDate,
                    teleSn,
                    simMainProvider,
                    noMainProvider,
                    simBackupProvider,
                    noBackupProvider,
                    rtuMerk,
                    rtuType,
                    rtuDate,
                    rtuSn,
                    batMerk,
                    batType,
                    batDate
                ).observe(this) { rtu ->
                    when (rtu) {
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
                            Log.e("AddKeypointsSpecLBSRECActivity", rtu.message.toString())
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
            showToast("Tolong lengkapi semua data")
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