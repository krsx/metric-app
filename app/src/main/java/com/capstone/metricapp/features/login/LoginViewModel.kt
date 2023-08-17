package com.capstone.metricapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase):ViewModel() {
    fun loginUser(email: String, password: String) = userUseCase.loginUser(email, password).asLiveData()

    fun saveUserToken(token: String) = viewModelScope.launch {
        userUseCase.saveUserToken(token)
    }
}