package com.tunahanozatac.cryptoapps.domain.repository

import com.tunahanozatac.cryptoapps.util.Resource
import com.tunahanozatac.cryptoapps.domain.model.CoinDetailUI
import com.tunahanozatac.cryptoapps.domain.model.CoinListUI
import com.tunahanozatac.cryptoapps.domain.model.CoinMarketsUI
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface CoinRepository {

    fun coinsMarkets(): Flow<Resource<List<CoinMarketsUI>>>

    fun searchCoin(searchQuery: String): Flow<Resource<List<CoinMarketsUI>>>

    fun coinById(coinId: String): Flow<Resource<CoinDetailUI>>

    fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>>

}