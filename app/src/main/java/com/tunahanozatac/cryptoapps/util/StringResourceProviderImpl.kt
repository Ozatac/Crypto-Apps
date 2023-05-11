package com.tunahanozatac.cryptoapps.util

import android.content.Context
import androidx.annotation.StringRes
import com.tunahanozatac.cryptoapps.domain.provider.StringResourceProvider
import javax.inject.Inject

class StringResourceProviderImpl @Inject constructor(
    private val context: Context
) : StringResourceProvider {

    override fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}