package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.domain.base.BaseUseCase
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import com.example.currencyconverter.presentation.model.ConversionRate
import javax.inject.Inject

class GetCurrencyConversionRateUseCase @Inject constructor(private val mainCurrencyRepository: MainCurrencyRepository) :
BaseUseCase<ConversionRate,GetCurrencyConversionRateUseCase.Response>(){
    data class Response(
        val data: Resource<CurrencyConversionResponse>
    )

    override suspend fun execute(params: ConversionRate): Response {
        return  Response(mainCurrencyRepository.getCurrencyConversionRate(params))
    }
}