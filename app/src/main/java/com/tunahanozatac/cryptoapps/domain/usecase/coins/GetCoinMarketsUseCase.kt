package com.tunahanozatac.cryptoapps.domain.usecase.coins

import com.tunahanozatac.cryptoapps.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinMarketsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.coinsMarkets()
}