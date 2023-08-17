package com.capstone.metricapp.features.add_keypoints.desc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.constans.keypointsType
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityAddKeypointsDescBinding
import com.capstone.metricapp.features.add_keypoints.specs.AddKeypointsSpecGIGHActivity
import com.capstone.metricapp.features.add_keypoints.specs.AddKeypointsSpecLBSRECActivity
import com.capstone.metricapp.features.add_keypoints.specs.AddKeypointsSpecScadatelActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddKeypointsDescActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddKeypointsDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddKeypointsDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPickKeypointsType()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            if (!binding.edAddKeypoints.text.isNullOrEmpty() && !binding.edAddKeypointsRegion.text.isNullOrEmpty() && !binding.dropdownMenu.text.isNullOrEmpty()) {
                val intentToAddKeypointSpec: Intent = when (binding.dropdownMenu.text.toString()) {
                    KeypointsType.SCADATEL.toString() -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsType.LBSREC.toString() -> {
                        Intent(this, AddKeypointsSpecLBSRECActivity::class.java)
                    }
                    KeypointsType.GIGH.toString() -> {
                        Intent(this, AddKeypointsSpecGIGHActivity::class.java)
                    }
                    else -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                }
                startActivity(intentToAddKeypointSpec)
            } else {
                showToast("Tolong lengkapi semua data")
            }
        }
    }

    private fun setupPickKeypointsType() {
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, keypointsType)
        binding.dropdownMenu.setAdapter(adapter)
        binding.dropdownMenu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.edAddKeypointsType.isHintEnabled = p0.isNullOrEmpty()
            }

        }
        )
    }

    private fun setupButtonNext() {
        val intentToAddSpec = when (binding.dropdownMenu.text.toString()) {
            KeypointsType.GIGH.type -> {
                Intent(this, AddKeypointsSpecGIGHActivity::class.java)
            }
            KeypointsType.LBSREC.type -> {
                Intent(this, AddKeypointsSpecLBSRECActivity::class.java)
            }
            KeypointsType.SCADATEL.type -> {
                Intent(this, AddKeypointsSpecScadatelActivity::class.java)
            }

            else -> Intent(this, AddKeypointsSpecGIGHActivity::class.java)
        }

        startActivity(intentToAddSpec)
    }

}