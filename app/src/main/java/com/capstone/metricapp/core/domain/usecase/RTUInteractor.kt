package com.capstone.metricapp.core.domain.usecase

import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.Common
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.repository.IRTURepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RTUInteractor @Inject constructor(private val rtuRepository: IRTURepository) :
    RTUUseCase {
    override fun getAllRTU(token: String): Flow<Resource<List<RTU>>> {
        return rtuRepository.getAllRTU(token)
    }

    override fun getRTUById(token: String, id: String): Flow<Resource<RTU>> {
        return rtuRepository.getRTUById(token, id)
    }

    override fun findRTUKeypoints(token: String, keyword: String): Flow<Resource<List<RTU>>> {
        return rtuRepository.findRTUKeypoints(token, keyword)
    }

    override fun deleteRTUKeypoint(token: String, id: String): Flow<Resource<Common>> {
        return deleteRTUKeypoint(token, id)
    }
}