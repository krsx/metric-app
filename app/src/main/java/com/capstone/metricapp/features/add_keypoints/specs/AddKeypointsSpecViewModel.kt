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

    fun getUserEmail() = userUseCase.getUserEmail().asLiveData()

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
        date: String,
        notes: String,
        device: String,
        email: String,
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
        date,
        notes, device, email,
    ).asLiveData()

    //RTU
    fun createLBSKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,
        telkom_merk: String,
        telkom_type: String,
        telkom_rangeVolt: String,
        telkom_date: String,
        telkom_sn: String,
        main_sim_provider: String,
        main_sim_number: String,
        backup_sim_provider: String,
        backup_sim_number: String,
        rtu_merk: String,
        rtu_type: String,
        rtu_date: String,
        rtu_sn: String,
        bat_merk: String,
        bat_type: String,
        bat_date: String,
        notes: String,
        device: String,
        username: String,
    ) = rtuUseCase.createLBSKeypoint(
        token,
        uniqueId,
        keypoint,
        region,
        telkom_merk,
        telkom_type,
        telkom_rangeVolt,
        telkom_date,
        telkom_sn,
        main_sim_provider,
        main_sim_number,
        backup_sim_provider,
        backup_sim_number,
        rtu_merk,
        rtu_type,
        rtu_date,
        rtu_sn,
        bat_merk,
        bat_type,
        bat_date,
        notes,
        device, username,
    ).asLiveData()

    fun createGIGHKeypoint(
        token: String,
        uniqueId: String,
        keypoint: String,
        region: String,
        telkom_merk: String,
        telkom_type: String,
        telkom_rangeVolt: String,
        telkom_date: String,
        telkom_sn: String,
        rect_merk: String,
        rect_type: String,
        rect_rangeVolt: String,
        rect_date: String,
        rect_sn: String,
        rtu_merk: String,
        rtu_type: String,
        rtu_date: String,
        rtu_sn: String,
        bat_merk: String,
        bat_type: String,
        bat_date: String,
        gat_merk: String,
        gat_type: String,
        gat_date: String,
        gat_sn: String,
        notes: String,
        device: String,
        username: String,
    ) = rtuUseCase.createGIGHKeypoint(
        token,
        uniqueId,
        keypoint,
        region,
        telkom_merk,
        telkom_type,
        telkom_rangeVolt,
        telkom_date,
        telkom_sn,
        rect_merk,
        rect_type,
        rect_rangeVolt,
        rect_date,
        rect_sn,
        rtu_merk,
        rtu_type,
        rtu_date,
        rtu_sn,
        bat_merk,
        bat_type,
        bat_date,
        gat_merk,
        gat_type,
        gat_date,
        gat_sn,
        notes,
        device,
        username
    ).asLiveData()
}