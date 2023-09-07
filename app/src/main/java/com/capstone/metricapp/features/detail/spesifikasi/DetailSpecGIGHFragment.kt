package com.capstone.metricapp.features.detail.spesifikasi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.FragmentDetailSpecGighBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSpecGIGHFragment : Fragment() {
    private var _binding: FragmentDetailSpecGighBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailSpecGighBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.getUserToken().observe(this) { token ->
            viewModel.id.observe(this) { id ->
                viewModel.rtu.observe(this) { rtu ->
                    setupInfo(rtu)
                }
            }
        }

        setupRefresh()
    }

    private fun setupRefresh() {
        binding.refDetailSpec.setOnRefreshListener {
            viewModel.getUserToken().observe(this) { token ->
                viewModel.id.observe(this) { id ->
                    viewModel.getRTUById(token, id).observe(this) { rtu ->

                        checkRTUDta(rtu)
                    }
                }
            }
        }
    }

    private fun checkRTUDta(rtu: Resource<RTU>) {
        when (rtu) {
            is Resource.Error -> {
                viewModel.setLoading(false)
                context?.showToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
            }
            is Resource.Loading -> {
                viewModel.setLoading(true)
            }
            is Resource.Message -> {
                Log.e("DetailSpecGIGH", rtu.message.toString())
            }
            is Resource.Success -> {
                viewModel.setLoading(false)
                setupInfo(rtu.data!!)
            }
        }
    }

    private fun setupInfo(data: RTU) {
        binding.apply {
            tvDetailTeleMerk.text = data.telkom_merk
            tvDetailTeleType.text = data.telkom_type
            tvDetailTeleRange.text = data.telkom_rangeVolt
            tvDetailTeleDate.text = data.telkom_date
            tvDetailTeleSn.text = data.telkom_sn

            tvDetailRectifierMerk.text = data.rect_merk
            tvDetailRectifierType.text = data.rect_type
            tvDetailRectifierRangeVoltage.text = data.rect_rangeVolt
            tvDetailRectifierDate.text = data.rect_date
            tvDetailRectifierSn.text = data.rect_sn

            tvDetailRtuMerk.text = data.rtu_merk
            tvDetailRtuType.text = data.rtu_type
            tvDetailRtuDate.text = data.rtu_date
            tvDetailRtuSn.text = data.rtu_sn

            tvDetailBateraiMerk.text = data.bat_merk
            tvDetailBateraiType.text = data.bat_type
            tvDetailBateraiDate.text = data.bat_date

            tvDetailGatewayMerk.text = data.gat_merk
            tvDetailGatewayType.text = data.gat_type
            tvDetailGatewayDate.text = data.gat_date
            tvDetailGatewaySn.text = data.gat_sn
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.refDetailSpec.isRefreshing = isLoading
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}