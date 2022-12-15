package com.example.currencyconverter.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConversionRate(
    val amount : String,
    val from : String,
    val to  : String,
) : Parcelable
