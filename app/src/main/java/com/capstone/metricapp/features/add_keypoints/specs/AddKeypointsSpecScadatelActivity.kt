package com.capstone.metricapp.features.add_keypoints.specs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecScadatelBinding
import com.capstone.metricapp.features.home.HomeActivity

class AddKeypointsSpecScadatelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecScadatelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsSpecScadatelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            if (!binding.edScadatelMerk.text.isNullOrEmpty() && !binding.edScadatelType.text.isNullOrEmpty() && !binding.edScadatelMainVolt.text.isNullOrEmpty() && !binding.edScadatelBackupVolt.text.isNullOrEmpty() && !binding.edScadatelOs.text.isNullOrEmpty() && !binding.edScadatelDate.text.isNullOrEmpty() && !binding.edNotes.text.isNullOrEmpty()) {
                showToast("Data keypoints baru telah tersimpan")
                val intentToHome = Intent(this, HomeActivity::class.java)
                startActivity(intentToHome)
            } else {
                showToast("Tolong isi semua data")
            }
        }
    }
}