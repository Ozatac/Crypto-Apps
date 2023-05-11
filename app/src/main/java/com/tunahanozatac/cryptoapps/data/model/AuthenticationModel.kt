package com.tunahanozatac.cryptoapps.data.model

data class AuthenticationModel(
    val email: String, val password: String, val confirmPassword: String = ""
)