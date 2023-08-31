package com.capstone.metricapp.features.detail.spesifikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.metricapp.databinding.FragmentDetailSpecLbsrecBinding


class DetailSpecLBSRECFragment : Fragment() {
    private var _binding: FragmentDetailSpecLbsrecBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailSpecLbsrecBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
    }
}