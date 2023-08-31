package com.capstone.metricapp.features.detail.spesifikasi

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.FragmentDetailSpecScadatelBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class DetailSpecScadatelFragment : Fragment() {
    private var _binding: FragmentDetailSpecScadatelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailSpecScadatelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.id.observe(this) {
            context?.showToast(it)
        }

        viewModel.getUserToken().observe(this) { token ->
            Log.e("TOKEN BRO", token.toString())
            viewModel.id.observe(this) { id ->
                Log.e("ID BRO", id.toString())

                viewModel.getScadatelById(token, id).observe(this) { scadatel ->
                    checkScadatelData(scadatel)
                }
            }
        }

        setupRefresh()
    }

    private fun setupRefresh() {
        binding.refDetailSpec.setOnRefreshListener {
            viewModel.getUserToken().observe(this) { token ->
                viewModel.id.observe(this) { id ->
                    viewModel.getScadatelById(token, id).observe(this) { scadatel ->
                        checkScadatelData(scadatel)
                    }
                }
            }
        }
    }

    private fun checkScadatelData(scadatel: Resource<Scadatel>) {
        when (scadatel) {
            is Resource.Error -> {
                showLoading(false)
                context?.showToast("Terjadi kesalahan, silahkan cek koneksi internet anda dan lakukan scan ulang")
            }
            is Resource.Loading -> {
                showLoading(true)
            }
            is Resource.Message -> {
                Log.e("DetailSpecScadatel", scadatel.message.toString())
            }
            is Resource.Success -> {
                showLoading(false)
                Log.e("SCADATEL DATA", scadatel.data.toString())
                setupInfo(scadatel.data!!)
            }
        }
    }


    private fun setupInfo(scadatel: Scadatel) {
        Log.e("TEST SCADATEL", scadatel.toString())
        binding.apply {
            tvDetailScadatelMerk.text = scadatel.merk
            tvDetailScadatelType.text = scadatel.type
            tvDetailScadatelMainVolt.text = scadatel.mainVolt
            tvDetailScadatelBackupVolt.text = scadatel.backupVolt
            tvDetailScadatelOs.text = scadatel.os
            tvDetailScadatelDate.text = DateUtil.convertDateKeypoints(scadatel.date) ?: ""
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.refDetailSpec.isRefreshing = isLoading
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}