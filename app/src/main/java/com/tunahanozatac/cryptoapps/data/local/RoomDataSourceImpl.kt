package com.tunahanozatac.cryptoapps.data.local

import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsEntity
import com.tunahanozatac.cryptoapps.domain.source.local.LocalDataSource
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(
    private val coinsDAO: CoinsDAO
) : LocalDataSource {

    override suspend fun insertCoinMarketsList(items: List<CoinMarketsEntity>) =
        coinsDAO.insertCoinMarkets(items)

    override suspend fun getCoinMarkets() = coinsDAO.getCoinMarkets()

    override suspend fun searchCoin(searchQuery: String) = coinsDAO.searchCoin(searchQuery)

    override suspend fun deleteCoinList() = coinsDAO.deleteCoinList()

    override suspend fun deleteCoinMarkets() = coinsDAO.deleteCoinMarkets()
}