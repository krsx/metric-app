package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.utils.datamapper.ScadatelDataMapper
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecScadatelBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecScadatelFragment(
    private val historyData: KeypointHistory
) : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecScadatelBinding? = null
    private val binding get() = _binding!!

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
        val newValue = ScadatelDataMapper.getNewValueFieldFromJSON(historyData.newValue)
        binding.apply {
            tvDetailScadatelMerk.text = newValue.merk
            tvDetailScadatelType.text = newValue.type
            tvDetailScadatelMainVolt.text = newValue.mainVolt
            tvDetailScadatelBackupVolt.text = newValue.backupVolt
            tvDetailScadatelOs.text = newValue.os
            tvDetailScadatelDate.text = newValue.date
        }
    }


}