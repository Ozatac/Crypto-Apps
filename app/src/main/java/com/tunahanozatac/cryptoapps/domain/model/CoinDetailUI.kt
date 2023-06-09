package com.tunahanozatac.cryptoapps.domain.model

data class CoinDetailUI(
    val name: String? = "",
    val coinId: String? = "",
    val hashingAlgorithm: String? = "",
    val description: String? = "",
    val image: String? = "",
    val currentPrice: Double? = 0.0,
    val priceChangePercentage24h: Double? = 0.0
)