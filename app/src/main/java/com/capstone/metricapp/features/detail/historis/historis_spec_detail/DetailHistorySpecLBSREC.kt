package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.utils.datamapper.RTUDataMapper
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecLbsRecBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecLBSREC(private val historyData: KeypointHistory) :
    BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecLbsRecBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        val newValue = RTUDataMapper.getNewValueFieldFromJSON(historyData.newValue)
        binding.apply {
            tvDetailTeleMerk.text = newValue.telkom_merk
            tvDetailTeleType.text = newValue.telkom_type
            tvDetailTeleRange.text = newValue.telkom_rangeVolt
            tvDetailTeleDate.text = newValue.telkom_date
            tvDetailTeleSn.text = newValue.telkom_sn

            tvDetailSimMainProvider.text = newValue.main_sim_provider
            tvDetailSimMainNo.text = newValue.main_sim_number

            tvDetailSimBackupProvider.text = newValue.backup_sim_provider
            tvDetailSimBackupNo.text = newValue.backup_sim_number

            tvDetailRtuMerk.text = newValue.rtu_merk
            tvDetailRtuType.text = newValue.rtu_type
            tvDetailRtuDate.text = newValue.rtu_date
            tvDetailRtuSn.text = newValue.rtu_sn

            tvDetailBateraiMerk.text = newValue.bat_merk
            tvDetailBateraiType.text = newValue.bat_type
            tvDetailBateraiDate.text = newValue.bat_date

            tvDetailNotes.text = newValue.notes
        }
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