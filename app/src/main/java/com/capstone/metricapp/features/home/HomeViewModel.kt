package com.capstone.metricapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase,
    private val userUseCase: UserUseCase
) :
    ViewModel() {
    fun getAllScadatel(token: String) = scadatelUseCase.getAllScadatel(token).asLiveData()

    fun getUserToken() = userUseCase.getUserToken().asLiveData()
}