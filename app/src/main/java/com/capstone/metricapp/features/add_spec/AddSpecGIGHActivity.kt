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
import com.capstone.metricapp.databinding.ActivityAddSpecGighBinding
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class AddSpecGIGHActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSpecGighBinding
    private val viewModel: AddSpecViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSpecGighBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val id = intent.getStringExtra(AddSpecLBSRECActivity.KEY_ID_KEYPOINTS)

        viewModel.getUserToken().observe(this) { token ->
            setupHeaderInfo(token, id!!)

            setupInitialSpecData(token, id)

            setupButtonAdd(token, id)
        }
    }

    private fun setupButtonAdd(token: String, id: String) {
        binding.btnSave.setOnClickListener {
            val teleMerk = binding.edTeleMerk.text.toString()
            val teleType = binding.edTeleType.text.toString()
            val teleRangeVolt = binding.edTeleRange.text.toString()
            val teleDate = binding.edTeleDate.text.toString()
            val teleSn = binding.edTeleSn.text.toString()

            val gatMerk = binding.edGateawayMerk.text.toString()
            val gatType = binding.edGateawayType.text.toString()
            val gatDate = binding.edGateawayDate.text.toString()
            val gatSn = binding.edGateawaySn.text.toString()

            val rectMerk = binding.edRectifierMerk.text.toString()
            val rectType = binding.edRectifierType.text.toString()
            val rectRangeVolt = binding.edRectifierRange.text.toString()
            val rectDate = binding.edRectifierDate.text.toString()
            val rectSn = binding.edRectifierSn.text.toString()

            val rtuMerk = binding.edRtuMerk.text.toString()
            val rtuType = binding.edRtuType.text.toString()
            val rtuDate = binding.edRtuDate.text.toString()
            val rtuSn = binding.edRtuSn.text.toString()

            val batMerk = binding.edBatteryMerk.text.toString()
            val batType = binding.edBatteryType.text.toString()
            val batDate = binding.edBatteryDate.text.toString()

            viewModel.updateSpecGIGH(
                token, id, teleMerk,
                teleType,
                teleRangeVolt,
                teleDate,
                teleSn,
                rectMerk,
                rectType,
                rectRangeVolt,
                rectDate, rectSn,
                rtuMerk,
                rtuType,
                rtuDate,
                rtuSn,
                batMerk,
                batType,
                batDate,
                gatMerk,
                gatType,
                gatDate,
                gatSn
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
                        Log.e("AddSpecGIGHActivity", rtu.message.toString())
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

                        edRtuMerk.setText(rtu.data?.rtu_merk)
                        edRtuType.setText(rtu.data?.rtu_type)
                        edRtuDate.setText(rtu.data?.rtu_date)
                        edRtuSn.setText(rtu.data?.rtu_sn)

                        edBatteryMerk.setText(rtu.data?.bat_merk)
                        edBatteryType.setText(rtu.data?.bat_type)
                        edBatteryDate.setText(rtu.data?.bat_date)

                        edRectifierMerk.setText(rtu.data?.rect_merk)
                        edRectifierType.setText(rtu.data?.rect_type)
                        edRectifierRange.setText(rtu.data?.rect_rangeVolt)
                        edRectifierDate.setText(rtu.data?.rect_date)
                        edRectifierSn.setText(rtu.data?.rect_sn)

                        edGateawayMerk.setText(rtu.data?.gat_merk)
                        edGateawayType.setText(rtu.data?.gat_type)
                        edGateawayDate.setText(rtu.data?.gat_date)
                        edGateawaySn.setText(rtu.data?.gat_sn)

                        edNotes.setText("")
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
                    Log.e("AddSpecLGIGHActivity", rtu.message.toString())
                }
                is Resource.Success -> {
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