package com.capstone.metricapp.features.detail.historis

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.ui.adapter.ScadatelHistoryKeypointAdapter
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.extractId
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.FragmentDetailHistoryBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailHistoryFragment : Fragment() {
    private var _binding: FragmentDetailHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.getUserToken().observe(this) { token ->
            viewModel.id.observe(this) { id ->
                when (extractId(id)) {
                    KeypointsType.LBSREC -> {

                    }
                    KeypointsType.GIGH -> {

                    }
                    KeypointsType.SCADATEL -> {
                        viewModel.getHistoryScadatel(token, id)
                            .observe(viewLifecycleOwner) { history ->
                                when (history) {
                                    is Resource.Error -> {
                                        showLoading(false)
                                        context?.showToast("Terjadi kesalahan, silahkan cek koneksi internet anda dan lakukan scan ulang")
                                    }
                                    is Resource.Loading -> {
                                        showLoading(true)
                                    }
                                    is Resource.Message -> {
                                        Log.e("DetailHistoryFragment", history.message.toString())
                                    }
                                    is Resource.Success -> {
                                        showLoading(false)
                                        viewModel.setScadatelHistoryData(history.data!!)
                                        Log.e("MASUK", history.data.toString())
                                        initScadatelRecyclerView(history.data)
                                    }
                                }
                            }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initScadatelRecyclerView(keypointHistory: List<KeypointHistory>) {
        val layoutManager = LinearLayoutManager(context)
        binding.rvHistory.layoutManager = layoutManager

        val adapter = ScadatelHistoryKeypointAdapter(keypointHistory, parentFragmentManager)
        binding.rvHistory.adapter = adapter

        adapter.setOnItemClickCallback(object : ScadatelHistoryKeypointAdapter.OnItemClickCallback {
            override fun onItemClicked(history: KeypointHistory) {

            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.refHistory.isRefreshing = isLoading
    }

    companion object {
        private const val SCADATEL_HISTORY_FRAGMENT_TAG = "Detail History Scadatel"
        private const val LBSREC_HISTORY_FRAGMENT_TAG = "Detail History LBS REC"
        private const val GIGH_HISTORY_FRAGMENT_TAG = "Detail History  GI GH"
    }
}