package com.tunahanozatac.cryptoapps.util

sealed class DetailUIEffect {
    data class SnackBar(val message: String) : DetailUIEffect()
}