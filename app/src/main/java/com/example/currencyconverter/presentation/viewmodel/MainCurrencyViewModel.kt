package com.example.currencyconverter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.domain.usecase.GetCurrencyConversionRateUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencyHistoryUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencySymbolsUseCase
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.model.HistoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    private var _currencyHistory = MutableLiveData<Resource<HistoryResponse>>()
    val currencyHistory: LiveData<Resource<HistoryResponse>> get() = _currencyHistory


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

    fun getCurrencyHistory(historyInfo: HistoryInfo){
        _currencyHistory.value = Resource.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            val listOfHistory =  mutableListOf<HistoryResponse>()

            val aDayHistory = async {
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }

            val twoDaysHistory = async {
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }

            val threeDaysHistory = async {
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }
            if(aDayHistory is Resource.Success<*>){
                listOfHistory.add(aDayHistory.await().data!!)
            }

            if(twoDaysHistory is Resource.Success<*>){
                listOfHistory.add(twoDaysHistory.await().data!!)
            }

            if(threeDaysHistory is Resource.Success<*>){
                listOfHistory.add(threeDaysHistory.await().data!!)
            }

            _currencyHistory.postValue(getCurrencyHistoryUseCase.execute(historyInfo).data)
        }
    }

}