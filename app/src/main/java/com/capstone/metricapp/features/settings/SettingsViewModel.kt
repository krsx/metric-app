package com.capstone.metricapp.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private var userUseCase: UserUseCase) : ViewModel() {
    fun deleteCache() = viewModelScope.launch {
        userUseCase.deleteCache()
    }
}