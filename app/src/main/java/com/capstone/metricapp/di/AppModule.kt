package com.capstone.metricapp.di

import com.capstone.metricapp.core.domain.usecase.*
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

    @Binds
    @ViewModelScoped
    abstract fun provideRTUUseCase(rtuInteractor: RTUInteractor): RTUUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase


}