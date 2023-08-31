package com.capstone.metricapp.features.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.usecase.RTUUseCase
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase,
    private val userUseCase: UserUseCase,
    private val rtuUseCase: RTUUseCase,
) : ViewModel() {
    private var _scadatel = MutableLiveData<Scadatel>()
    var scadatel: LiveData<Scadatel> = _scadatel

    private var _rtu = MutableLiveData<RTU>()
    var rtu: LiveData<RTU> = _rtu

    fun setScadatelData(input: Scadatel) {
        _scadatel.value = input
    }

    fun setRTUData(input: RTU) {
        _rtu.value = input
    }

    fun getScadatelById(token: String, id: String) =
        scadatelUseCase.getScadatelById(token, id).asLiveData()

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    fun getRTUById(token: String, id: String) = rtuUseCase.getRTUById(token, id).asLiveData()
}