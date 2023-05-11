package com.tunahanozatac.cryptoapps.data.remote

import com.tunahanozatac.cryptoapps.domain.source.remote.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val coinService: CoinService
) : RemoteDataSource {

    override suspend fun getCoinMarkets() = coinService.getCoinMarkets()

    override suspend fun getCoinById(coinId: String) = coinService.getCoinById(coinId)

}