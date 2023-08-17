package com.capstone.metricapp.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    fun getUserToken() = userUseCase.getUserToken().asLiveData()
}