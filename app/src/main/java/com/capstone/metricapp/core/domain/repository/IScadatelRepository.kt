package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.Scadatel
import kotlinx.coroutines.flow.Flow

interface IScadatelRepository {
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

    fun deleteScadatelKeypoint(token: String, id: String): Flow<Resource<Common>>
}