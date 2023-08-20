package com.capstone.metricapp.features.scan

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.databinding.FragmentSuccessQRBinding
import com.capstone.metricapp.features.add_keypoints.desc.AddKeypointsDescActivity
import com.capstone.metricapp.features.detail.DetailKeypointActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

@RequiresApi(Build.VERSION_CODES.O)
class SuccessQRFragment(private val scadatel: Scadatel) : BottomSheetDialogFragment() {
    private var _binding: FragmentSuccessQRBinding? = null
    private val binding get() = _binding!!

    //later will be changed to sharedPreferences
    private var keypointsType: KeypointsType = KeypointsType.SCADATEL

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessQRBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val intentToAddKeypointsDesc =
                Intent(requireContext(), AddKeypointsDescActivity::class.java)
            startActivity(intentToAddKeypointsDesc)
        }

        binding.btnDetail.setOnClickListener {
            val intentToDetailKeypoint =
                Intent(requireContext(), DetailKeypointActivity::class.java)

            startActivity(intentToDetailKeypoint)
        }

        setupKeypointInfo()
    }


    private fun setupKeypointInfo() {
        binding.apply {
            tvDialogRegion.text = scadatel.region
            tvDialogKeypoint.text = scadatel.keypoint
            tvDialogDate.text = DateUtil.convertDateKeypoints(scadatel.dateCreated)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(scadatel: Scadatel): SuccessQRFragment {
            return SuccessQRFragment(scadatel)
        }

        const val KEY_ID_SCADATEL = "key_id_scadatel"
    }
}