package com.tunahanozatac.cryptoapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsEntity
import com.tunahanozatac.cryptoapps.domain.model.CoinMarketsUI

@Dao
interface CoinsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinMarkets(items: List<CoinMarketsEntity>)

    @Query("SELECT * FROM coin_markets ORDER BY id ASC")
    suspend fun getCoinMarkets(): List<CoinMarketsEntity>

    @Query("SELECT * FROM coin_list WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    suspend fun searchCoin(searchQuery: String): List<CoinMarketsUI>

    @Query("DELETE FROM coin_list")
    suspend fun deleteCoinList()

    @Query("DELETE FROM coin_markets")
    suspend fun deleteCoinMarkets()
}