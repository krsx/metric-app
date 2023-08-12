package com.capstone.metricapp.features.scan

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.capstone.metricapp.core.data.Resource
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
class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var qrScanner: CodeScanner
    private val scanViewModel: ScanViewModel by viewModels()

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

    private fun qrScanner() {
        qrScanner = CodeScanner(this, binding.qrScanner)
        qrScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE

            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    SuccessQRFragment().isCancelable = true
                    scanViewModel.getScadatelById(it.text).observe(this@ScanActivity) {scadatel ->
                        when(scadatel){
                            is Resource.Error -> {
                                showToast("Terjadi kesalahan, silahkan cek koneksi internet anda dan lakukan scan ulang")
                            }
                            is Resource.Loading -> {

                            }
                            is Resource.Message -> {

                            }
                            is Resource.Success -> {
                               lifecycleScope.launch {
                                   scanViewModel.scadatelData.value = scadatel.data!!
                               }
                                SuccessQRFragment().show(supportFragmentManager, "Support QR Fragment")
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

        binding.qrScanner.setOnClickListener {
            qrScanner.startPreview()
        }
    }


    companion object {
        private const val CAMERA_REQUEST_CODE = 101
    }
}