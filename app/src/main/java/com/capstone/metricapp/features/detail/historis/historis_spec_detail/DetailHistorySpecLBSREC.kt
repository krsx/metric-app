package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecLbsRecBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecLBSREC : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecLbsRecBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHistorySpecLbsRecBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}