package com.example.currencyconverter.data.network.service

import com.example.currencyconverter.common.NetworkUtil.API_KEY
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.data.model.SymbolResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyConverterService {

    @GET("symbols")
    suspend fun getCurrencySymbols(
        @Query("access_key") apikey : String = API_KEY
    ): SymbolResponse

    @GET("convert")
    suspend fun getConvertedCurrency(
        @Query("amount") amount: String,
        @Query("to") to: String,
        @Query("from") from: String
    ): CurrencyConversionResponse

    @GET("/{date}")
    suspend fun getConvertedHistory(
        @Path("date") date: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): HistoryResponse
}