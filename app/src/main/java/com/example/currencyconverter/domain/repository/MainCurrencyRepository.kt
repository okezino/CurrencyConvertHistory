package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.data.model.SymbolResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.presentation.model.ConversionRate

interface MainCurrencyRepository {

   suspend fun  getCurrencySymbols() : Resource<SymbolResult>

    suspend fun getCurrencyConversionRate(conversionRate: ConversionRate) : Resource<CurrencyConversionResponse>

    suspend fun getCurrencyHistory(date : String , base : String, symbols : String)  : Resource<HistoryResponse>
}