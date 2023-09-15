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
import com.capstone.metricapp.core.utils.showLongToast
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

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.getUserToken().observe(this) { token ->
            viewModel.id.observe(this) { id ->
                viewModel.scadatel.observe(this) { scadatel ->
                    viewModel.getUserEmail().observe(this) { email ->
                        setupInfo(scadatel, email)
                    }
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
                        viewModel.getUserEmail().observe(this) { email ->
                            checkScadatelData(scadatel, email)
                        }
                    }
                }
            }
        }
    }

    private fun checkScadatelData(scadatel: Resource<Scadatel>, email: String) {
        when (scadatel) {
            is Resource.Error -> {
                viewModel.setLoading(false)
                context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
            }
            is Resource.Loading -> {
                viewModel.setLoading(true)
            }
            is Resource.Message -> {
                Log.e("DetailSpecScadatel", scadatel.message.toString())
            }
            is Resource.Success -> {
                viewModel.setLoading(false)
                setupInfo(scadatel.data!!, email)
            }
        }
    }


    private fun setupInfo(scadatel: Scadatel, email: String) {
        binding.apply {
            tvDetailScadatelMerk.text = scadatel.merk
            tvDetailScadatelType.text = scadatel.type
            tvDetailScadatelMainVolt.text = scadatel.mainVolt
            tvDetailScadatelBackupVolt.text = scadatel.backupVolt
            tvDetailScadatelOs.text = scadatel.os
            tvDetailScadatelDate.text = scadatel.date
            tvDetailScadatelNotes.text = scadatel.notes
            tvDetailScadatelDevice.text = scadatel.device
            tvDetailScadatelUsername.text = scadatel.username
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.refDetailSpec.isRefreshing = isLoading
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}