package com.capstone.metricapp.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private var _scadatel = MutableLiveData<Scadatel>()
    var scadatel: LiveData<Scadatel> = _scadatel

    fun setId(id: String) {
        _id.value = id
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun setScadatelData(input: Scadatel) {
        _scadatel.value = input
    }

    fun getScadatelById(token: String, id: String) =
        scadatelUseCase.getScadatelById(token, id).asLiveData()

    fun getHistoryScadatel(token: String, id: String) =
        scadatelUseCase.getHistoryScadatel(token, id).asLiveData()

    fun getRTUById(token: String, id: String) = rtuUseCase.getRTUById(token, id).asLiveData()

    fun getUserToken() = userUseCase.getUserToken().asLiveData()

    fun getUserDivision() = userUseCase.getUserDivision().asLiveData()
}