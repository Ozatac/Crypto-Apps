package com.tunahanozatac.cryptoapps.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunahanozatac.cryptoapps.data.model.coinlist.CoinListEntity
import com.tunahanozatac.cryptoapps.data.model.coinmarket.CoinMarketsEntity

@Database(
    entities = [CoinListEntity::class, CoinMarketsEntity::class], version = 1, exportSchema = false
)
abstract class CoinsRoomDB : RoomDatabase() {
    abstract fun coinsDAO(): CoinsDAO
}