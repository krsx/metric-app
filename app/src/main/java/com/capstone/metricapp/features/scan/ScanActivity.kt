package com.capstone.metricapp.features.scan

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.utils.cleanId
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.extractId
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityScanBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@AndroidEntryPoint
@FlowPreview
@ExperimentalCoroutinesApi
@RequiresApi(Build.VERSION_CODES.O)

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var qrScanner: CodeScanner
    private var scadatelData: Scadatel? = null
    private val viewModel: ScanViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupPermissions()
        qrScanner()
    }

    override fun onResume() {
        super.onResume()

        qrScanner.startPreview()
    }

    override fun onPause() {
        qrScanner.releaseResources()

        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            sendRequestPermissions()
        }
    }

    private fun sendRequestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showLongToast("Anda perlu memberikan izn akses kamera untuk dapat menggunakan fitur ini")
            } else {
                //success
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun qrScanner() {
        qrScanner = CodeScanner(this, binding.qrScanner)
        qrScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE

            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback { result ->
                runOnUiThread {
                    viewModel.getUserToken().observe(this@ScanActivity) { token ->
                        when (extractId(result.text)) {
                            KeypointsType.LBSREC -> {

                            }
                            KeypointsType.GIGH -> {

                            }
                            KeypointsType.SCADATEL -> {
                                val cleanId = cleanId(result.text)
                                viewModel.getScadatelById(token, cleanId)
                                    .observe(this@ScanActivity) { scadatel ->
                                        when (scadatel) {
                                            is Resource.Error -> {
                                                showLoading(false)
                                                showToast("Terjadi kesalahan, silahkan cek koneksi internet anda dan lakukan scan ulang $cleanId")
                                            }
                                            is Resource.Loading -> {
                                                showLoading(true)
                                            }
                                            is Resource.Message -> {
                                                showLoading(false)
                                            }
                                            is Resource.Success -> {
                                                showLoading(false)

                                                lifecycleScope.launch {
                                                    scadatelData = Scadatel(
                                                        id = scadatel.data?.id!!,
                                                        uniqueId = scadatel.data.uniqueId,
                                                        keypoint = scadatel.data.keypoint,
                                                        region = scadatel.data.region,
                                                        merk = scadatel.data.merk,
                                                        type = scadatel.data.type,
                                                        mainVolt = scadatel.data.mainVolt,
                                                        backupVolt = scadatel.data.backupVolt,
                                                        os = scadatel.data.os,
                                                        date = scadatel.data.date,
                                                        dateCreated = scadatel.data.dateCreated
                                                    )

                                                    viewModel.setScadatelData(scadatelData!!)

                                                    SuccessQRFragment(KeypointsType.SCADATEL).show(
                                                        supportFragmentManager,
                                                        SUCCESS_FRAGMENT_TAG
                                                    )
                                                }
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    showLongToast("Terdapat permasalahan pada kamera, silahkan buka kembali halaman ini")
                }
            }
        }

        startPreview()
    }

    private fun startPreview() {
        binding.qrScanner.setOnClickListener {
            val existingFragment = supportFragmentManager.findFragmentByTag(SUCCESS_FRAGMENT_TAG)

            if (!(existingFragment != null && existingFragment.isAdded)) {
                qrScanner.startPreview()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    companion object {
        private const val CAMERA_REQUEST_CODE = 101
        private const val SUCCESS_FRAGMENT_TAG = "Support QR Fragment"
    }
}