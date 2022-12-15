package com.example.currencyconverter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.domain.usecase.GetCurrencyConversionRateUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencyHistoryUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencySymbolsUseCase
import com.example.currencyconverter.presentation.model.ConversionRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainCurrencyViewModel @Inject constructor(
    private val getCurrencySymbolsUseCase: GetCurrencySymbolsUseCase,
    private val getCurrencyHistoryUseCase: GetCurrencyHistoryUseCase,
    private val getCurrencyConversionRateUseCase: GetCurrencyConversionRateUseCase
) : ViewModel() {

    private var _currencySymbols = MutableLiveData<Resource<SymbolResult>>()
    val currencySymbols: LiveData<Resource<SymbolResult>> get() = _currencySymbols

    private var _currencyConverted = MutableLiveData<Resource<CurrencyConversionResponse>>()
    val currencyConverted: LiveData<Resource<CurrencyConversionResponse>> get() = _currencyConverted


    fun getCurrencySymbols() {
        _currencySymbols .value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _currencySymbols.postValue(getCurrencySymbolsUseCase.execute(Unit).data)
        }

    }


    fun getCurrencyConverted(conversionRate: ConversionRate){
        _currencyConverted.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _currencyConverted.postValue(getCurrencyConversionRateUseCase.execute(conversionRate).data)
        }
    }

}