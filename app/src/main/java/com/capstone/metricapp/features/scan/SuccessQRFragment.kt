package com.capstone.metricapp.features.scan

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.databinding.FragmentSuccessQRBinding
import com.capstone.metricapp.features.add_spec.AddSpecGIGHActivity
import com.capstone.metricapp.features.add_spec.AddSpecLBSRECActivity
import com.capstone.metricapp.features.add_spec.AddSpecScadatelActivity
import com.capstone.metricapp.features.detail.DetailKeypointActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class SuccessQRFragment(private val type: KeypointsType) :
    BottomSheetDialogFragment() {
    private var _binding: FragmentSuccessQRBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ScanViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessQRBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonAdd()

        setupButtonDetail()

        setupKeypointInfo()
    }

    private fun setupButtonDetail() {
        binding.btnDetail.setOnClickListener {
            when (type) {
                KeypointsType.LBSREC -> {
                    viewModel.rtu.observe(this) { rtu ->
                        val intentToDetailRTU =
                            Intent(requireContext(), DetailKeypointActivity::class.java)
                        intentToDetailRTU.putExtra(KEY_ID_KEYPOINTS, rtu.id)
                        startActivity(intentToDetailRTU)
                    }
                }
                KeypointsType.GIGH -> {
                    viewModel.rtu.observe(this) { rtu ->
                        val intentToDetailGIGH =
                            Intent(requireContext(), DetailKeypointActivity::class.java)
                        intentToDetailGIGH.putExtra(KEY_ID_KEYPOINTS, rtu.id)
                        startActivity(intentToDetailGIGH)
                    }
                }
                KeypointsType.SCADATEL -> {
                    viewModel.scadatel.observe(this) { scadatel ->
                        val intentToDetailScadatel =
                            Intent(requireContext(), DetailKeypointActivity::class.java)
                        intentToDetailScadatel.putExtra(KEY_ID_KEYPOINTS, scadatel.id)
                        startActivity(intentToDetailScadatel)
                    }
                }
            }
        }
    }

    private fun setupButtonAdd() {
        binding.btnAdd.setOnClickListener {
            when (type) {
                KeypointsType.LBSREC -> {
                    viewModel.rtu.observe(this) { rtu ->
                        val intentToAddSpecLBSREC =
                            Intent(requireContext(), AddSpecLBSRECActivity::class.java)
                        intentToAddSpecLBSREC.putExtra(KEY_ID_KEYPOINTS, rtu.uniqueId)
                        startActivity(intentToAddSpecLBSREC)
                    }
                }
                KeypointsType.GIGH -> {
                    viewModel.rtu.observe(this) { rtu ->
                        val intentToAddSpecGIGH =
                            Intent(requireContext(), AddSpecGIGHActivity::class.java)
                        intentToAddSpecGIGH.putExtra(KEY_ID_KEYPOINTS, rtu.uniqueId)
                        startActivity(intentToAddSpecGIGH)
                    }
                }
                KeypointsType.SCADATEL -> {
                    viewModel.scadatel.observe(this) { scadatel ->
                        val intentToAddSpecScadatel =
                            Intent(requireContext(), AddSpecScadatelActivity::class.java)
                        intentToAddSpecScadatel.putExtra(KEY_ID_KEYPOINTS, scadatel.uniqueId)
                        startActivity(intentToAddSpecScadatel)
                    }
                }
            }
        }
    }

    private fun setupKeypointInfo() {
        when (type) {
            KeypointsType.LBSREC -> {
                viewModel.rtu.observe(this) { rtu ->
                    binding.apply {
                        binding.tvDialogKeypoint.text = rtu.keypoint
                        binding.tvDialogDate.text = DateUtil.convertDateKeypoints(rtu.dateCreated)
                        binding.tvDialogRegion.text = rtu.region
                    }
                }
            }
            KeypointsType.GIGH -> {
                viewModel.rtu.observe(this) { rtu ->
                    binding.apply {
                        binding.tvDialogKeypoint.text = rtu.keypoint
                        binding.tvDialogDate.text = DateUtil.convertDateKeypoints(rtu.dateCreated)
                        binding.tvDialogRegion.text = rtu.region
                    }
                }
            }
            KeypointsType.SCADATEL -> {
                viewModel.scadatel.observe(this) { scadatel ->
                    Log.e("INI SUDAH", "INI SUDAH")
                    binding.apply {
                        binding.tvDialogKeypoint.text = scadatel.keypoint
                        binding.tvDialogDate.text =
                            DateUtil.convertDateKeypoints(scadatel.dateCreated)
                        binding.tvDialogRegion.text = scadatel.region
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}