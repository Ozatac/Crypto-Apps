package com.tunahanozatac.cryptoapps.domain.source.remote

import com.tunahanozatac.cryptoapps.data.model.coindetail.response.CoinDetailResponse
import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsResponse

interface RemoteDataSource {

    suspend fun getCoinMarkets(): List<CoinMarketsResponse>

    suspend fun getCoinById(coinId: String): CoinDetailResponse

}