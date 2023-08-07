package com.capstone.metricapp.features.scan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.metricapp.databinding.FragmentSuccessQRBinding
import com.capstone.metricapp.features.add_keypoints.desc.AddKeypointsDescActivity
import com.capstone.metricapp.features.detail.spesifikasi.DetailSpecGIGHFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SuccessQRFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSuccessQRBinding? = null
    private val binding get() = _binding!!

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
            val intentToAddKeypointsDesc = Intent(requireContext(), AddKeypointsDescActivity::class.java)
            startActivity(intentToAddKeypointsDesc)
        }

        binding.btnDetail.setOnClickListener {
            val intentToDetailKeypoints = Intent(requireContext(), DetailSpecGIGHFragment::class.java)
            startActivity(intentToDetailKeypoints)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}