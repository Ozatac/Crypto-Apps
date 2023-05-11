package com.tunahanozatac.cryptoapps.util

import android.text.TextUtils
import android.util.Patterns
import java.util.*

fun String.isValidEmailAddress() =
    TextUtils.isEmpty(this).not() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() =
    TextUtils.isEmpty(this).not() && this.length >= 6
