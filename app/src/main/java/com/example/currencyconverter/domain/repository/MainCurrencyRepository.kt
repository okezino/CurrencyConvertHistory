package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResponse
import com.example.currencyconverter.presentation.model.ConversionRate

interface MainCurrencyRepository {

    fun getCurrencySymbols() : Resource<SymbolResponse>

    fun getCurrencyConversionRate(conversionRate: ConversionRate) : Resource<CurrencyConversionResponse>

    fun getCurrencyHistory(base : String, symbols : String)
}