package com.tunahanozatac.cryptoapps.domain.usecase.favourites

import com.tunahanozatac.cryptoapps.domain.model.FavouritesUI
import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class DeleteFromFavouritesUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(favouritesUI: FavouritesUI) =
        authenticator.deleteFromFavourites(favouritesUI)
}