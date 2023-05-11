package com.tunahanozatac.cryptoapps.di

import com.tunahanozatac.cryptoapps.data.local.CoinsDAO
import com.tunahanozatac.cryptoapps.data.local.RoomDataSourceImpl
import com.tunahanozatac.cryptoapps.data.remote.CoinService
import com.tunahanozatac.cryptoapps.data.remote.RemoteDataSourceImpl
import com.tunahanozatac.cryptoapps.domain.source.local.LocalDataSource
import com.tunahanozatac.cryptoapps.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        coinService: CoinService,
    ): RemoteDataSource =
        RemoteDataSourceImpl(coinService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        coinsDAO: CoinsDAO
    ): LocalDataSource = RoomDataSourceImpl(coinsDAO)
}