package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResponse
import com.example.currencyconverter.domain.base.BaseUseCase
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import javax.inject.Inject

class GetCurrencySymbolsUseCase @Inject constructor(private val mainCurrencyRepository: MainCurrencyRepository)
    : BaseUseCase<Unit, GetCurrencySymbolsUseCase.Response>() {
    data class Response(
        val data: Resource<SymbolResponse>
    )

    override suspend fun execute(params: Unit): Response {
      return Response(mainCurrencyRepository.getCurrencySymbols())
    }
}