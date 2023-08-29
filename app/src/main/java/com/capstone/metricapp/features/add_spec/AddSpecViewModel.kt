package com.capstone.metricapp.features.add_spec

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.RTUUseCase
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddSpecViewModel @Inject constructor(
    private val rtuUseCase: RTUUseCase,
    private val scadatelUseCase: ScadatelUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    fun updateSpecScadatel(
        token: String,
        id: String,
        keypoint: String,
        region: String?,
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?
    ) = scadatelUseCase.updateSpecScadatel(
        token,
        id,
        keypoint,
        region,
        merk,
        type,
        mainVolt,
        backupVolt,
        os,
        date
    )
}