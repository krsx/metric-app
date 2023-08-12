package com.capstone.metricapp.core.domain.repository

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Scadatel
import kotlinx.coroutines.flow.Flow

interface IScadatelRepository {
    fun getAllScadatel(): Flow<Resource<List<Scadatel>>>
    fun getScadatelById(id: String): Flow<Resource<Scadatel>>

}