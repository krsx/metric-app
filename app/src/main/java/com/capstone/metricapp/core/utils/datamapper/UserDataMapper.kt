package com.capstone.metricapp.core.utils.datamapper

import com.capstone.metricapp.core.data.source.remote.response.LoginUser
import com.capstone.metricapp.core.data.source.remote.response.RegisterUser
import com.capstone.metricapp.core.domain.model.User

object UserDataMapper {
    fun mapLoginResponseToDomain(input: LoginUser?): User = User(
        id = input?.id!!,
        email = input.email!!,
        password = input.password!!,
        division = input.division!!,
        createdAt = input.createdAt!!,
        token = input.token!!
    )

    fun mapRegisterResponseToDomain(input: RegisterUser?): User = User(
        id = input?.id!!,
        email = input.email!!,
        password = input.password!!,
        division = input.division!!,
        createdAt = input.createdAt!!,
        token = input.token!!
    )
}