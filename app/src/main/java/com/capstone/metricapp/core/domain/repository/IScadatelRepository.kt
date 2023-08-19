package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Scadatel
import kotlinx.coroutines.flow.Flow

interface IScadatelRepository {
    fun getAllScadatel(token: String): Flow<Resource<List<Scadatel>>>

    fun getScadatelById(token: String, id: String): Flow<Resource<Scadatel>>

    fun findScadatelKeypoints(token: String, keyword: String): Flow<Resource<List<Scadatel>>>
}