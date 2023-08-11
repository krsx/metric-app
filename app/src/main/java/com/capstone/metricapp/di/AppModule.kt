package com.capstone.metricapp.di

import com.capstone.metricapp.core.domain.usecase.ScadatelInteractor
import com.capstone.metricapp.core.domain.usecase.ScadatelUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideScadatelUseCase(scadatelInteractor: ScadatelInteractor): ScadatelUseCase
}