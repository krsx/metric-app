package com.capstone.metricapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase
) : ViewModel() {
    fun getAllScadatel() = scadatelUseCase.getAllScadatel().asLiveData()
}