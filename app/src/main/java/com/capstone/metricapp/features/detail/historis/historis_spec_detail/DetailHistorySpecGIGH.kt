package com.capstone.metricapp.features.detail.historis.historis_spec_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.utils.datamapper.RTUDataMapper
import com.capstone.metricapp.databinding.FragmentDetailHistorySpecGiGhBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistorySpecGIGH(private val historyData: KeypointHistory) :
    BottomSheetDialogFragment() {
    private var _binding: FragmentDetailHistorySpecGiGhBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHistorySpecGiGhBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

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

            tvDetailRectifierMerk.text = newValue.rect_merk
            tvDetailRectifierType.text = newValue.rect_type
            tvDetailRectifierRangeVoltage.text = newValue.rect_rangeVolt
            tvDetailRectifierDate.text = newValue.rect_date
            tvDetailRectifierSn.text = newValue.rect_sn

            tvDetailRtuMerk.text = newValue.rtu_merk
            tvDetailRtuType.text = newValue.rtu_type
            tvDetailRtuDate.text = newValue.rtu_date
            tvDetailRtuSn.text = newValue.rtu_sn

            tvDetailBateraiMerk.text = newValue.bat_merk
            tvDetailBateraiType.text = newValue.bat_type
            tvDetailBateraiDate.text = newValue.bat_date

            tvDetailGatewayMerk.text = newValue.gat_merk
            tvDetailGatewayType.text = newValue.gat_type
            tvDetailGatewayDate.text = newValue.gat_date
            tvDetailGatewaySn.text = newValue.gat_sn

            tvDetailNotes.text = newValue.notes
            tvDetailDevice.text = newValue.device
            tvDetailUsername.text = newValue.username
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}