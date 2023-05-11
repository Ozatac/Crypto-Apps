package com.tunahanozatac.cryptoapps.domain.provider

interface StringResourceProvider {
    fun getString(stringResId: Int): String
}