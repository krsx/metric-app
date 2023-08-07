package com.capstone.metricapp.features.add_keypoints.specs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityAddKeypointsSpecLbsrecBinding
import com.capstone.metricapp.features.home.HomeActivity

class AddKeypointsSpecLBSRECActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsSpecLbsrecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsSpecLbsrecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            if (!binding.edTeleMerk.text.isNullOrEmpty() && !binding.edTeleType.text.isNullOrEmpty() && !binding.edTeleRange.text.isNullOrEmpty() && !binding.edTeleSn.text.isNullOrEmpty()
                && !binding.edSimMainProvider.text.isNullOrEmpty() && !binding.edNoSimCard.text.isNullOrEmpty() && !binding.edSimBackupProvider.text.isNullOrEmpty() && !binding.edNoSimCardBackup.text.isNullOrEmpty()
                && !binding.edRtuMerk.text.isNullOrEmpty() && !binding.edRtuType.text.isNullOrEmpty() && !binding.edRtuDate.text.isNullOrEmpty() && !binding.edRtuDate.text.isNullOrEmpty()
                && !binding.edBatteryMerk.text.isNullOrEmpty() && !binding.edBatteryType.text.isNullOrEmpty() && !binding.edBatteryDate.text.isNullOrEmpty()
                && !binding.edNotes.text.isNullOrEmpty()
            ) {
                showToast("Data keypoints baru telah tersimpan")
                val intentToHome = Intent(this, HomeActivity::class.java)
                startActivity(intentToHome)
            } else {
                showToast("Tolong lengkapi semua data")
            }
        }
    }
}