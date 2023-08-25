package com.capstone.metricapp.core.di

import com.capstone.metricapp.core.data.repository.RTURepository
import com.capstone.metricapp.core.data.repository.ScadatelRepository
import com.capstone.metricapp.core.data.repository.UserRepository
import com.capstone.metricapp.core.domain.repository.IRTURepository
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import com.capstone.metricapp.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideScadatelRepository(scadatelRepository: ScadatelRepository): IScadatelRepository

    @Binds
    abstract fun provideRTURepository(rtuRepository: RTURepository): IRTURepository

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository): IUserRepository

}