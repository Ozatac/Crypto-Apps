package com.tunahanozatac.cryptoapps.domain.usecase.main

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class GetFirebaseUserUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.getCurrentUser()
}