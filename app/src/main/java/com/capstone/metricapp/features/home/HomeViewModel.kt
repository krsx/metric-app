package com.capstone.metricapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.RTUUseCase
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase,
    private val userUseCase: UserUseCase,
    private val rtuUseCase: RTUUseCase,
) : ViewModel() {
    fun getAllScadatel(token: String) = scadatelUseCase.getAllScadatel(token).asLiveData()

    fun findScadatelKeypoints(token: String, keyword: String) =
        scadatelUseCase.findScadatelKeypoints(token, keyword).asLiveData()

    fun getAllRTU(token: String) = rtuUseCase.getAllRTU(token).asLiveData()

    fun findRTUKeypoints(token: String, keyword: String) =
        rtuUseCase.findRTUKeypoints(token, keyword).asLiveData()

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    fun getUserDivision() = userUseCase.getUserDivision().asLiveData()
}