package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecGiGhBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecGIGH : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecGiGhBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHistorySpecGiGhBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}