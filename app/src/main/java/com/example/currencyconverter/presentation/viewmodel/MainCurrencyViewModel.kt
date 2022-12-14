package com.example.currencyconverter.presentation.viewmodel

import com.example.currencyconverter.domain.usecase.GetCurrencyConversionRateUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencyHistoryUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencySymbolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainCurrencyViewModel @Inject constructor(
    private val getCurrencySymbolsUseCase: GetCurrencySymbolsUseCase,
    private val getCurrencyHistoryUseCase: GetCurrencyHistoryUseCase,
    private val getCurrencyConversionRateUseCase: GetCurrencyConversionRateUseCase
) {


}