package com.example.currencyconverter.data.network.service

import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterService {

    @GET("symbols")
    suspend fun getCurrencySymbols(): SymbolResponse

    @GET("convert")
    suspend fun getConvertedCurrency(
        @Query("amount") amount: String,
        @Query("to") to: String,
        @Query("from") from: String
    ): CurrencyConversionResponse
}