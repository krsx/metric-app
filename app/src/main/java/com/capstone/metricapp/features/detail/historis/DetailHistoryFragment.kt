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
import com.capstone.metricapp.core.ui.adapter.HistoryKeypointAdapter
import com.capstone.metricapp.core.utils.constans.KeypointsType
import com.capstone.metricapp.core.utils.extractId
import com.capstone.metricapp.core.utils.showLongToast
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

        viewModel.noHistory.observe(this) {
            showNoHistory(it)
        }

        setupRefresh()
    }

    private fun setupRefresh() {
        binding.refHistory.setOnRefreshListener {
            viewModel.getUserToken().observe(this) { token ->
                viewModel.id.observe(this) { id ->
                    when (extractId(id)) {
                        KeypointsType.LBSREC -> {
                            viewModel.getHistoryRTU(token, id)
                                .observe(viewLifecycleOwner) { history ->
                                    when (history) {
                                        is Resource.Error -> {
                                            showLoading(false)
                                            viewModel.setNoHistory(true)
//                                            context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")

                                        }
                                        is Resource.Loading -> {
                                            showLoading(true)
                                        }
                                        is Resource.Message -> {
                                            Log.e(
                                                "DetailHistoryFragment",
                                                history.message.toString()
                                            )
                                        }
                                        is Resource.Success -> {
                                            showLoading(false)
                                            viewModel.setNoHistory(false)

                                            viewModel.setKeypointHistoryData(history.data!!)
                                            initHistoryRecyclerView(
                                                history.data,
                                                KeypointsType.LBSREC
                                            )
                                        }
                                    }
                                }
                        }
                        KeypointsType.GIGH -> {
                            viewModel.getHistoryRTU(token, id)
                                .observe(viewLifecycleOwner) { history ->
                                    when (history) {
                                        is Resource.Error -> {
                                            showLoading(false)
                                            viewModel.setNoHistory(true)
                                            context?.showLongToast("${history.data} + ${history.message}")

//                                            context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
                                        }
                                        is Resource.Loading -> {
                                            showLoading(true)
                                        }
                                        is Resource.Message -> {
                                            Log.e(
                                                "DetailHistoryFragment",
                                                history.message.toString()
                                            )
                                        }
                                        is Resource.Success -> {
                                            showLoading(false)
                                            viewModel.setNoHistory(false)

                                            viewModel.setKeypointHistoryData(history.data!!)
                                            initHistoryRecyclerView(
                                                history.data,
                                                KeypointsType.GIGH
                                            )
                                        }
                                    }
                                }
                        }
                        KeypointsType.SCADATEL -> {
                            viewModel.getHistoryScadatel(token, id)
                                .observe(viewLifecycleOwner) { history ->
                                    when (history) {
                                        is Resource.Error -> {
                                            viewModel.setNoHistory(true)
                                            showLoading(false)
//                                            context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
                                        }
                                        is Resource.Loading -> {
                                            showLoading(true)
                                        }
                                        is Resource.Message -> {
                                            Log.e(
                                                "DetailHistoryFragment",
                                                history.message.toString()
                                            )
                                        }
                                        is Resource.Success -> {
                                            showLoading(false)
                                            viewModel.setNoHistory(false)

                                            viewModel.setKeypointHistoryData(history.data!!)
                                            initHistoryRecyclerView(
                                                history.data,
                                                KeypointsType.SCADATEL
                                            )
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        viewModel.getUserToken().observe(this) { token ->
            viewModel.id.observe(this) { id ->
                when (extractId(id)) {
                    KeypointsType.LBSREC -> {
                        viewModel.getHistoryRTU(token, id).observe(viewLifecycleOwner) { history ->
                            when (history) {
                                is Resource.Error -> {
                                    showLoading(false)
                                    viewModel.setNoHistory(true)

//                                    context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
                                }
                                is Resource.Loading -> {
                                    showLoading(true)
                                }
                                is Resource.Message -> {
                                    Log.e("DetailHistoryFragment", history.message.toString())
                                }
                                is Resource.Success -> {
                                    showLoading(false)
                                    viewModel.setNoHistory(false)

                                    viewModel.setKeypointHistoryData(history.data!!)
                                    initHistoryRecyclerView(history.data, KeypointsType.LBSREC)
                                }
                            }
                        }
                    }
                    KeypointsType.GIGH -> {
                        viewModel.getHistoryRTU(token, id).observe(viewLifecycleOwner) { history ->
                            when (history) {
                                is Resource.Error -> {
                                    showLoading(false)
                                    viewModel.setNoHistory(true)

//                                    context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
                                }
                                is Resource.Loading -> {
                                    showLoading(true)
                                }
                                is Resource.Message -> {
                                    Log.e("DetailHistoryFragment", history.message.toString())
                                }
                                is Resource.Success -> {
                                    showLoading(false)
                                    viewModel.setNoHistory(false)

                                    viewModel.setKeypointHistoryData(history.data!!)
                                    initHistoryRecyclerView(history.data, KeypointsType.GIGH)
                                }
                            }
                        }
                    }
                    KeypointsType.SCADATEL -> {
                        viewModel.getHistoryScadatel(token, id)
                            .observe(viewLifecycleOwner) { history ->
                                when (history) {
                                    is Resource.Error -> {
                                        showLoading(false)
                                        viewModel.setNoHistory(true)

//                                        context?.showLongToast("Terjadi kesalahan, silahkan cek koneksi internet dan buka kembali aplikasi METRIC")
                                    }
                                    is Resource.Loading -> {
                                        showLoading(true)
                                    }
                                    is Resource.Message -> {
                                        Log.e("DetailHistoryFragment", history.message.toString())
                                    }
                                    is Resource.Success -> {
                                        showLoading(false)
                                        viewModel.setNoHistory(false)

                                        viewModel.setKeypointHistoryData(history.data!!)
                                        initHistoryRecyclerView(
                                            history.data,
                                            KeypointsType.SCADATEL
                                        )
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

    private fun initHistoryRecyclerView(
        keypointHistory: List<KeypointHistory>,
        keypointsType: KeypointsType
    ) {
        val layoutManager = LinearLayoutManager(context)
        binding.rvHistory.layoutManager = layoutManager

        val adapter = HistoryKeypointAdapter(keypointHistory, parentFragmentManager, keypointsType)
        binding.rvHistory.adapter = adapter

        adapter.setOnItemClickCallback(object : HistoryKeypointAdapter.OnItemClickCallback {
            override fun onItemClicked(history: KeypointHistory) {

            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.refHistory.isRefreshing = isLoading
    }

    private fun showNoHistory(isNoHistory: Boolean) {
        binding.tvNoHistory.visibility = if (isNoHistory) View.VISIBLE else View.GONE
    }
}