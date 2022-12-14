package com.example.currencyconverter.data.model

data class CurrencyConversionResponse(
    val date: String,
    val info: Info?,
    val query: Query?,
    val result: Double,
    val success: Boolean
)