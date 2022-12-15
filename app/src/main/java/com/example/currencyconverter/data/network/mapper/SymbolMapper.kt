package com.example.currencyconverter.data.network.mapper

import com.example.currencyconverter.data.model.Symbols

fun Symbols.toListOfCurrencySymbol(): Map<String, String> {
    return mapOf(
        "AED" to AED,
        "AFN" to AFN,
        "ALL" to ALL,
        "AMD" to AMD,
        "EGP" to EGP,
        "EUR" to EUR,
        "JPY" to JPY,
        "USD" to USD
    )
}