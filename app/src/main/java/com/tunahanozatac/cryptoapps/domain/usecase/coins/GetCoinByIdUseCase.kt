package com.tunahanozatac.cryptoapps.domain.usecase.coins

import com.tunahanozatac.cryptoapps.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String) = coinRepository.coinById(coinId)
}