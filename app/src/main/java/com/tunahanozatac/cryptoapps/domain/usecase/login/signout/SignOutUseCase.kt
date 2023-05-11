package com.tunahanozatac.cryptoapps.domain.usecase.login.signout

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke() = authenticator.signOut()
}