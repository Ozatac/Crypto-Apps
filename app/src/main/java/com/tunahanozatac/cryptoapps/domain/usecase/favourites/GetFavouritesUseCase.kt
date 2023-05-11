package com.tunahanozatac.cryptoapps.domain.usecase.favourites

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.getFavourites()
}