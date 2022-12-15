package com.example.currencyconverter.data.model

data class HistoryResponse(
    val success: Boolean,
    val historical: Boolean,
    val date: String,
    val timestamp: Long,
    val base: String,
    val rates: Rates
)
