package com.tunahanozatac.cryptoapps.data.remote

import com.tunahanozatac.cryptoapps.data.model.coindetail.response.CoinDetailResponse
import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsResponse
import com.tunahanozatac.cryptoapps.util.Constants.COIN_BY_ID
import com.tunahanozatac.cryptoapps.util.Constants.COIN_MARKETS
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET(COIN_MARKETS)
    suspend fun getCoinMarkets(): List<CoinMarketsResponse>

    @GET(COIN_BY_ID)
    suspend fun getCoinById(@Path("id") coinId: String): CoinDetailResponse
}