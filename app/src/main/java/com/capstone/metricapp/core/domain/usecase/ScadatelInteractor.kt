package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.data.repository.ScadatelRepository
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScadatelInteractor @Inject constructor(private val scadatelRepository: IScadatelRepository) :
    ScadatelUseCase {
    override fun getAllScadatel(): Flow<Resource<List<Scadatel>>> {
        return scadatelRepository.getAllScadatel()
    }

    override fun getScadatelById(id: String): Flow<Resource<Scadatel>> {
        TODO("Not yet implemented")
    }

}