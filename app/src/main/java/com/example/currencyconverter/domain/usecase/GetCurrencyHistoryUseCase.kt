package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import javax.inject.Inject

class GetCurrencyHistoryUseCase@Inject constructor(private val mainCurrencyRepository: MainCurrencyRepository) {
}