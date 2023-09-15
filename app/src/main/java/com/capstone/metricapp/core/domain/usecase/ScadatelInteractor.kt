package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class ScadatelInteractor @Inject constructor(private val scadatelRepository: IScadatelRepository) :
    ScadatelUseCase {
    override fun getAllScadatel(token: String): Flow<Resource<List<Scadatel>>> {
        return scadatelRepository.getAllScadatel(token)
    }

    override fun getScadatelById(token: String, id: String): Flow<Resource<Scadatel>> {
        return scadatelRepository.getScadatelById(token, id)
    }

    override fun findScadatelKeypoints(
        token: String,
        keyword: String
    ): Flow<Resource<List<Scadatel>>> {
        return scadatelRepository.findScadatelKeypoints(token, keyword)
    }

    override fun createScadatelKeypoint(
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
        username: String,
    ): Flow<Resource<Scadatel>> {
        return scadatelRepository.createScadatelKeypoint(
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
            notes,
            device,
            username
        )
    }

    override fun updateSpecScadatel(
        token: String,
        id: String,
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?,
        notes: String?,
        device: String?,
        username: String?
    ): Flow<Resource<Scadatel>> {
        return scadatelRepository.updateSpecScadatel(
            token,
            id,
            merk,
            type,
            mainVolt,
            backupVolt,
            os,
            date,
            notes,
            device, username,
        )
    }

    override fun getHistoryScadatel(
        token: String,
        uniqueId: String,
    ): Flow<Resource<List<KeypointHistory>>> {
        return scadatelRepository.getHistoryScadatel(token, uniqueId)
    }

    override fun deleteScadatelKeypoint(token: String, id: String): Flow<Resource<Common>> {
        return scadatelRepository.deleteScadatelKeypoint(token, id)
    }

    override fun exportScadatelDataToPDF(token: String, id: String): Flow<Resource<ResponseBody>> {
        return scadatelRepository.exportScadatelDataToPDF(token, id)
    }

    override fun exportScadatelDataToExcel(
        token: String,
        id: String
    ): Flow<Resource<ResponseBody>> {
        return scadatelRepository.exportScadatelDataToExcel(token, id)
    }

}