package com.example.currencyconverter.data.repository

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.data.network.mapper.toListOfCurrencySymbol
import com.example.currencyconverter.data.network.service.CurrencyConverterService
import com.example.currencyconverter.domain.base.BaseRepositoryService
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import com.example.currencyconverter.presentation.model.ConversionRate
import javax.inject.Inject

class MainCurrencyRepositoryImp @Inject constructor(private val currencyConverterService: CurrencyConverterService) : MainCurrencyRepository, BaseRepositoryService() {
    override suspend fun getCurrencySymbols(): Resource<SymbolResult> = executeRequest {
        try {
            val response = currencyConverterService.getCurrencySymbols()
            Resource.Success(SymbolResult(response.symbols?.toListOfCurrencySymbol()?.map { it.key } ?: listOf()))

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Som")
        }

    }

    override suspend fun getCurrencyConversionRate(conversionRate: ConversionRate): Resource<CurrencyConversionResponse>  = executeRequest {
        try {
            val response = currencyConverterService.getConvertedCurrency(conversionRate.amount, conversionRate.to, conversionRate.from)
            Resource.Success(response)

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Som")
        }
    }

    override suspend fun getCurrencyHistory(base: String, symbols: String) {
        TODO("Not yet implemented")
    }


}