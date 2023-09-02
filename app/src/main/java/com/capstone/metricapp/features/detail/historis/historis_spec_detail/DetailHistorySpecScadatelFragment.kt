package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecScadatelBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecScadatelFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecScadatelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentDetailHistorySpecScadatelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        viewModel.getUserToken().observe(this) { token ->
            viewModel.id.observe(this) { id ->
                viewModel.getScadatelById(token, id).observe(this) { scadatel ->
                    when (scadatel) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Message -> {

                        }
                        is Resource.Success -> {

                        }
                    }
                }
            }
        }
    }
}