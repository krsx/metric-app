package com.capstone.metricapp.features.add_keypoints.specs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.RTUUseCase
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddKeypointsSpecViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val scadatelUseCase: ScadatelUseCase,
    private val rtuUseCase: RTUUseCase
) : ViewModel() {

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    //SCADATEL
    fun createScadatelKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,
        merk: String,
        type: String,
        mainVolt: String,
        backupVolt: String,
        os: String,
        date: String
    ) = scadatelUseCase.createScadatelKeypoint(
        token,
        uniqueId,
        keypoint,
        region,
        merk,
        type,
        mainVolt,
        backupVolt,
        os,
        date
    ).asLiveData()

    //RTU
}