package com.capstone.metricapp.features.add_keypoints.desc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.metricapp.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddKeypointsDescViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModel() {

    fun getUserDivision() = userUseCase.getUserDivision().asLiveData()
}