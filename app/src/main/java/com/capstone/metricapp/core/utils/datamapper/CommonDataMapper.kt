package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.data.source.remote.response.CommonResponse
import com.capstone.metricapp.core.domain.model.Common

object CommonDataMapper {
    fun mapCommonResponseToDomain(input: CommonResponse): Common = Common(
        status = input.status.toString(),
        message = input.message.toString()
    )
}