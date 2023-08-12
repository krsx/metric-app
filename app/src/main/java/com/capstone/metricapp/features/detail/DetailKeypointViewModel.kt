package com.capstone.metricapp.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailKeypointViewModel @Inject constructor(private val scadatelUseCase: ScadatelUseCase):ViewModel() {
    fun getScadatelById(id: String) = scadatelUseCase.getScadatelById(id).asLiveData()
}