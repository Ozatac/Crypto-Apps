package com.tunahanozatac.cryptoapps.di

import android.app.Application
import com.tunahanozatac.cryptoapps.domain.provider.StringResourceProvider
import com.tunahanozatac.cryptoapps.domain.provider.WorkerProvider
import com.tunahanozatac.cryptoapps.util.StringResourceProviderImpl
import com.tunahanozatac.cryptoapps.infrastructure.worker.WorkerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourceProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideWorkerProvider(application: Application): WorkerProvider {
        return WorkerProviderImpl(application)
    }
}