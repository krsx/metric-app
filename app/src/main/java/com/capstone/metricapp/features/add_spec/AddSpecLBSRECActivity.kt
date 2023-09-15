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
import com.capstone.metricapp.databinding.ActivityAddSpecLbsrecBinding
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)

class AddSpecLBSRECActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecLbsrecBinding
    private val viewModel: AddSpecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecLbsrecBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val id = intent.getStringExtra(KEY_ID_KEYPOINTS)
        Log.e("LOG ID BERAPA", id?.length.toString() + id)


        viewModel.getUserToken().observe(this) { token ->
            setupHeaderInfo(token, id!!)

            setupInitialSpecData(token, id)

            viewModel.getUserEmail().observe(this) { email ->
                setupButtonAdd(token, id, email)
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    private fun setupButtonAdd(token: String, id: String, email: String) {
        binding.btnSave.setOnClickListener {
            val teleMerk = binding.edTeleMerk.text.toString()
            val teleType = binding.edTeleType.text.toString()
            val teleRangeVolt = binding.edTeleRange.text.toString()
            val teleDate = binding.edTeleDate.text.toString()
            val teleSn = binding.edTeleSn.text.toString()

            val mainSimProvider = binding.edSimMainProvider.text.toString()
            val mainSimNumber = binding.edNoSimCard.text.toString()

            val backupSimProvider = binding.edSimBackupProvider.text.toString()
            val backupSimNumber = binding.edNoSimCardBackup.text.toString()

            val rtuMerk = binding.edRtuMerk.text.toString()
            val rtuType = binding.edRtuType.text.toString()
            val rtuDate = binding.edRtuDate.text.toString()
            val rtuSn = binding.edRtuSn.text.toString()

            val batMerk = binding.edBatteryMerk.text.toString()
            val batType = binding.edBatteryType.text.toString()
            val batDate = binding.edBatteryDate.text.toString()

            val notes = binding.edNotes.text.toString()

            viewModel.updateSpecLBSREC(
                token,
                id,
                teleMerk,
                teleType,
                teleRangeVolt,
                teleDate,
                teleSn,
                mainSimProvider,
                mainSimNumber,
                backupSimProvider,
                backupSimNumber,
                rtuMerk,
                rtuType,
                rtuDate,
                rtuSn,
                batMerk,
                batType,
                batDate,
                notes,
                "${Build.MANUFACTURER} ${Build.MODEL}",
                email
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
                        Log.e("AddSpecLBSRECActivity", rtu.message.toString())
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

    private fun setupInitialSpecData(token: String, id: String) {
        viewModel.getRTUById(token, id).observe(this) { rtu ->
            when (rtu) {
                is Resource.Error -> {
                    showLoading(false)
                    showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                }
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Message -> {
                    Log.e("AddSpecLBSRECActivity", rtu.message.toString())
                }
                is Resource.Success -> {
                    showLoading(false)

                    binding.apply {
                        edTeleMerk.setText(rtu.data?.telkom_merk)
                        edTeleType.setText(rtu.data?.telkom_type)
                        edTeleRange.setText(rtu.data?.telkom_rangeVolt)
                        edTeleDate.setText(rtu.data?.telkom_date)
                        edTeleSn.setText(rtu.data?.telkom_sn)

                        edSimMainProvider.setText(rtu.data?.main_sim_provider)
                        edNoSimCard.setText(rtu.data?.main_sim_number)

                        edSimBackupProvider.setText(rtu.data?.backup_sim_provider)
                        edNoSimCardBackup.setText(rtu.data?.backup_sim_number)

                        edRtuMerk.setText(rtu.data?.rtu_merk)
                        edRtuType.setText(rtu.data?.rtu_type)
                        edRtuDate.setText(rtu.data?.rtu_date)
                        edRtuSn.setText(rtu.data?.rtu_sn)

                        edBatteryMerk.setText(rtu.data?.bat_merk)
                        edBatteryType.setText(rtu.data?.bat_type)
                        edBatteryDate.setText(rtu.data?.bat_date)

                        edNotes.setText(rtu.data?.notes)
                    }
                }
            }
        }
    }

    private fun setupHeaderInfo(token: String, id: String) {
        viewModel.getRTUById(token, id).observe(this) { rtu ->
            when (rtu) {
                is Resource.Error -> {
                    showLoading(false)
                    showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                }
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Message -> {
                    Log.e("AddSpecLBSRECActivity", rtu.message.toString())
                }
                is Resource.Success -> {
                    showLoading(false)

                    binding.tvAddSpecRegion.text =
                        rtu.data?.region + " - ${rtu.data?.uniqueId}"
                    binding.tvAddSpecKeypoint.text = rtu.data?.keypoint
                    binding.tvAddSpecDate.text =
                        DateUtil.convertDateKeypoints(rtu.data?.dateCreated!!)
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