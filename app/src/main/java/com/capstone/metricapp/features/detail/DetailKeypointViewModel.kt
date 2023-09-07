package com.capstone.metricapp.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.usecase.RTUUseCase
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailKeypointViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase,
    private val userUseCase: UserUseCase,
    private val rtuUseCase: RTUUseCase,
) :
    ViewModel() {
    private var _id = MutableLiveData<String>()
    var id: LiveData<String> = _id

    private var _noHistory = MutableLiveData<Boolean>()
    var noHistory: LiveData<Boolean> = _noHistory

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private var _scadatel = MutableLiveData<Scadatel>()
    var scadatel: LiveData<Scadatel> = _scadatel

    private var _rtu = MutableLiveData<RTU>()
    var rtu: LiveData<RTU> = _rtu

    private var _keypointListHistory = MutableLiveData<List<KeypointHistory>>()
    var keypointListHistory: LiveData<List<KeypointHistory>> = _keypointListHistory

    fun setId(id: String) {
        _id.value = id
    }

    fun setNoHistory(isNoHistory: Boolean) {
        _noHistory.value = isNoHistory
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun setScadatelData(input: Scadatel) {
        _scadatel.value = input
    }

    fun setRTUData(input: RTU) {
        _rtu.value = input
    }

    fun setKeypointHistoryData(input: List<KeypointHistory>) {
        _keypointListHistory.value = input
    }

    fun getScadatelById(token: String, id: String) =
        scadatelUseCase.getScadatelById(token, id).asLiveData()

    fun getHistoryScadatel(token: String, id: String) =
        scadatelUseCase.getHistoryScadatel(token, id).asLiveData()

    fun deleteScadatelKeypoint(token: String, id: String) =
        scadatelUseCase.deleteScadatelKeypoint(token, id).asLiveData()

    fun getRTUById(token: String, id: String) = rtuUseCase.getRTUById(token, id).asLiveData()

    fun getHistoryRTU(token: String, id: String) = rtuUseCase.getHistoryRTU(token, id).asLiveData()

    fun deleteRTUKeypoint(token: String, id: String) =
        rtuUseCase.deleteRTUKeypoint(token, id).asLiveData()

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    fun getUserDivision() = userUseCase.getUserDivision().asLiveData()
}