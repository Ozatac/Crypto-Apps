package com.tunahanozatac.cryptoapps.util

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnackBar(msg: String) {
    Snackbar.make(this, msg, 1500).show()
}

fun String.convertStringForSearchQuery() = "%$this%"

fun EditText.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")
    this.addTextChangedListener {
        query.value = it.toString()
    }
    return query
}