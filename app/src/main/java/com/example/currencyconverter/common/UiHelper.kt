package com.example.currencyconverter.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

object UiHelper {

    fun View.showSnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG)
            .show()
    }
}