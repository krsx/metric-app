package com.capstone.metricapp.features.scan

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityScanBinding

class ScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScanBinding
    private lateinit var qrScanner: CodeScanner
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
        when(requestCode){
            CAMERA_REQUEST_CODE -> if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                showLongToast("Anda perlu memberikan izn akses kamera untuk dapat menggunakan fitur ini")
            }else{
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
            scanMode = ScanMode.CONTINUOUS

            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    showToast(it.text)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    showLongToast("Terdapat permasalahan pada kamera, silahkan buka kembali halaman ini")
                }
            }
        }
    }


    companion object {
        private const val CAMERA_REQUEST_CODE = 101
    }
}