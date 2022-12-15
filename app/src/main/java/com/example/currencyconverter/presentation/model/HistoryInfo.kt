package com.example.currencyconverter.presentation.model

import com.example.currencyconverter.data.model.Symbols

data class HistoryInfo(
    val date : String,
    val base : String,
    val symbols: String
)