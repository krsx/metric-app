package com.capstone.metricapp.features.add_keypoints.desc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.constans.KeypointsTypeId
import com.capstone.metricapp.core.utils.constans.keypointsIdType
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
                    KeypointsTypeId.GATEWAY.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.GPS.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.ROUTER.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.SERVER.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.SWITCH.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.RADIO_SUARA.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.WORK_STATION.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.RADIO_DATA.keypoint -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                    KeypointsTypeId.LBS.keypoint -> {
                        Intent(this, AddKeypointsSpecLBSRECActivity::class.java)
                    }
                    KeypointsTypeId.RECLOSER.keypoint -> {
                        Intent(this, AddKeypointsSpecLBSRECActivity::class.java)
                    }
                    KeypointsTypeId.GI.keypoint -> {
                        Intent(this, AddKeypointsSpecGIGHActivity::class.java)
                    }
                    KeypointsTypeId.GH.keypoint -> {
                        Intent(this, AddKeypointsSpecGIGHActivity::class.java)
                    }
                    else -> {
                        Intent(this, AddKeypointsSpecScadatelActivity::class.java)
                    }
                }
                intentToAddKeypointSpec.putExtra(
                    KEY_KEYPOINT_TYPE,
                    binding.dropdownMenu.text.toString()
                )
                intentToAddKeypointSpec.putExtra(
                    KEY_REGION,
                    binding.edAddKeypointsRegion.text.toString()
                )
                intentToAddKeypointSpec.putExtra(
                    KEY_KEYPOINT,
                    binding.edAddKeypoints.text.toString()
                )

                startActivity(intentToAddKeypointSpec)
            } else {
                showToast("Tolong lengkapi semua data")
            }
        }
    }

    private fun setupPickKeypointsType() {
        val adapter = ArrayAdapter(this, R.layout.item_list_dropdown, keypointsIdType)
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

    companion object {
        private const val KEY_KEYPOINT = "key_keypoint"
        private const val KEY_KEYPOINT_TYPE = "key_keypoint_type"
        private const val KEY_REGION = "key_region"
    }
}

