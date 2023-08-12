package com.capstone.metricapp.features.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
@FlowPreview
@ExperimentalCoroutinesApi
class ScanViewModel @Inject constructor(
    private val scadatelUseCase: ScadatelUseCase
) : ViewModel() {

    val scadatelData = MutableStateFlow(
        Scadatel(
            id = "",
            uniqueId = "",
            keypoint = "",
            region = "",
            merk = "",
            type = "",
            mainVolt = "",
            backupVolt = "",
            os = "",
            date = "",
            dateCreated = ""
        )
    )

    val getScadatelData = scadatelData.asLiveData()

    fun getScadatelById(id: String) = scadatelUseCase.getScadatelById(id).asLiveData()

}