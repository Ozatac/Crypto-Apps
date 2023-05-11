package com.tunahanozatac.cryptoapps.data.repository

import androidx.room.withTransaction
import com.tunahanozatac.cryptoapps.domain.source.remote.RemoteDataSource
import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.data.mappers.*
import com.tunahanozatac.cryptoapps.domain.repository.CoinRepository
import com.tunahanozatac.cryptoapps.domain.source.local.LocalDataSource
import com.tunahanozatac.cryptoapps.data.local.CoinsRoomDB
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Named
import kotlin.time.Duration

class CoinRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coinsRoomDB: CoinsRoomDB,
    @Named("Default") private val coContextDefault: CoroutineDispatcher
) : CoinRepository {

    private var job: Job? = null

    override fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>> =
        channelFlow {
            job?.cancel()
            job = CoroutineScope(coContextDefault).launch {
                while (true) {
                    send(Resource.Loading)
                    val data = remoteDataSource.getCoinById(coinId).toCoinDetailUI()
                    data.currentPrice?.let {
                        send(Resource.Success(it))
                    }
                    delay(period)
                }
            }

            awaitClose {
                channel.close()
                job?.cancel()
            }
        }.catch {
            emit(Resource.Error(it))
        }

    override fun coinsMarkets() = flow {
        emit(Resource.Loading)
        val cache = localDataSource.getCoinMarkets()
        if (cache.isNotEmpty()) {
            emit(Resource.Success(cache.toCoinMarketsUI()))
        }
        val response = try {
            remoteDataSource.getCoinMarkets()
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
            null
        }

        response?.let { data ->
            coinsRoomDB.withTransaction {
                localDataSource.deleteCoinMarkets()
                localDataSource.insertCoinMarketsList(data.toCoinMarketsEntity())
            }

            emit(Resource.Success(localDataSource.getCoinMarkets().toCoinMarketsUI()))
        }
    }

    override fun searchCoin(searchQuery: String) = flow {
        emit(Resource.Loading)
        emit(Resource.Success(localDataSource.searchCoin(searchQuery)))
    }.catch {
        emit(Resource.Error(it))
    }

    override fun coinById(coinId: String) = flow {
        emit(Resource.Loading)
        emit(Resource.Success(remoteDataSource.getCoinById(coinId).toCoinDetailUI()))
    }.catch {
        emit(Resource.Error(it))
    }
}