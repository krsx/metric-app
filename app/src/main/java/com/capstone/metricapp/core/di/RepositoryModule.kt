package com.capstone.metricapp.core.di

import com.capstone.metricapp.core.data.repository.ScadatelRepository
import com.capstone.metricapp.core.domain.repository.IScadatelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideScadatelRepository(scadatelRepository: ScadatelRepository): IScadatelRepository
}