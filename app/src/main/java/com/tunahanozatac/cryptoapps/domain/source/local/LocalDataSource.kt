package com.tunahanozatac.cryptoapps.domain.source.local

import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsEntity
import com.tunahanozatac.cryptoapps.domain.model.CoinMarketsUI

interface LocalDataSource {
    suspend fun insertCoinMarketsList(items: List<CoinMarketsEntity>)

    suspend fun getCoinMarkets(): List<CoinMarketsEntity>

    suspend fun searchCoin(searchQuery: String): List<CoinMarketsUI>

    suspend fun deleteCoinList()

    suspend fun deleteCoinMarkets()
}