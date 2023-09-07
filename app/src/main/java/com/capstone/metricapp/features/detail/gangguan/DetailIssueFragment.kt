package com.capstone.metricapp.features.detail.gangguan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.metricapp.databinding.FragmentDetailIssueBinding

class DetailIssueFragment : Fragment() {
    private var _binding: FragmentDetailIssueBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailIssueBinding.inflate(inflater, container, false)
        return binding.root
    }
}