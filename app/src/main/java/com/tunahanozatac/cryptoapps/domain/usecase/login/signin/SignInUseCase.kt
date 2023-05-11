package com.tunahanozatac.cryptoapps.domain.usecase.login.signin

import com.tunahanozatac.cryptoapps.domain.repository.Authenticator
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    operator fun invoke(email: String, password: String) =
        authenticator.signInWithEmailAndPassword(email, password)
}