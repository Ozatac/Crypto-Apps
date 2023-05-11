package com.tunahanozatac.cryptoapps.domain.usecase.favourites

import com.tunahanozatac.cryptoapps.domain.model.CoinDetailUI
import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class AddToFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(coinDetailUI: CoinDetailUI) = authenticator.addToFavourites(coinDetailUI)
}