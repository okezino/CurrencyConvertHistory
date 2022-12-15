package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.domain.base.BaseUseCase
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import com.example.currencyconverter.presentation.model.HistoryInfo
import javax.inject.Inject

class GetCurrencyHistoryUseCase@Inject constructor(private val mainCurrencyRepository: MainCurrencyRepository)
    : BaseUseCase<HistoryInfo, GetCurrencyHistoryUseCase.Response>() {

    data class Response(
        val data: Resource<HistoryResponse>
    )

    override suspend fun execute(params: HistoryInfo): Response {
       return Response(mainCurrencyRepository.getCurrencyHistory(params.date, params.base,params.symbols))
    }
}