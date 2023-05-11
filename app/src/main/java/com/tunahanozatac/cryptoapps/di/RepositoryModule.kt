package com.tunahanozatac.cryptoapps.di

import com.tunahanozatac.cryptoapps.data.local.CoinsRoomDB
import com.tunahanozatac.cryptoapps.data.repository.CoinRepositoryImpl
import com.tunahanozatac.cryptoapps.domain.repository.CoinRepository
import com.tunahanozatac.cryptoapps.domain.source.local.LocalDataSource
import com.tunahanozatac.cryptoapps.domain.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        coinsRoomDB: CoinsRoomDB,
        @Named("Default") coContextDefault: CoroutineDispatcher
    ): CoinRepository =
        CoinRepositoryImpl(remoteDataSource, localDataSource, coinsRoomDB, coContextDefault)
}