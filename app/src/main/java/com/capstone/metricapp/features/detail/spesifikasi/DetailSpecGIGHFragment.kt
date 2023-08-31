package com.capstone.metricapp.features.detail.spesifikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.metricapp.databinding.FragmentDetailSpecGighBinding
import com.capstone.metricapp.features.detail.DetailKeypointViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSpecGIGHFragment : Fragment() {
    private var _binding: FragmentDetailSpecGighBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailKeypointViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailSpecGighBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}