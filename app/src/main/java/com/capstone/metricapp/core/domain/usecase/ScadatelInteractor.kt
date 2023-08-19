package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import kotlinx.coroutines.flow.Flow
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

}