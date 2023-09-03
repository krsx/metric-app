package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.KeypointHistory
import com.capstone.metricapp.core.domain.model.Scadatel
import kotlinx.coroutines.flow.Flow

interface ScadatelUseCase {
    fun getAllScadatel(token: String): Flow<Resource<List<Scadatel>>>

    fun getScadatelById(token: String, id: String): Flow<Resource<Scadatel>>

    fun findScadatelKeypoints(token: String, keyword: String): Flow<Resource<List<Scadatel>>>

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
    ): Flow<Resource<Scadatel>>

    fun updateSpecScadatel(
        //unique ID should not be changed
        token: String,
        id: String,
        merk: String?,
        type: String?,
        mainVolt: String?,
        backupVolt: String?,
        os: String?,
        date: String?
    ): Flow<Resource<Scadatel>>

    fun getHistoryScadatel(
        token: String,
        uniqueId: String,
    ): Flow<Resource<List<KeypointHistory>>>

    fun deleteScadatelKeypoint(token: String, id: String): Flow<Resource<Common>>
}