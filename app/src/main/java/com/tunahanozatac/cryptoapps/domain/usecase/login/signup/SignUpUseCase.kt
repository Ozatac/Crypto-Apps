package com.tunahanozatac.cryptoapps.domain.usecase.login.signup

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(email: String, password: String) =
        authenticator.signUpWithEmailAndPassword(email, password)
}