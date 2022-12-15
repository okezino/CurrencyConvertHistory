package com.example.currencyconverter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.common.NetworkUtil.NETWORK_CALL_ERROR
import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.Days
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.domain.usecase.GetCurrencyConversionRateUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencyHistoryUseCase
import com.example.currencyconverter.domain.usecase.GetCurrencySymbolsUseCase
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.model.HistoryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private var _currencyHistory = MutableLiveData<Resource<List<HistoryResponse>>>()
    val currencyHistory: LiveData<Resource<List<HistoryResponse>>> get() = _currencyHistory


    fun getCurrencySymbols() {
        _currencySymbols.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _currencySymbols.postValue(getCurrencySymbolsUseCase.execute(Unit).data)
        }

    }


    fun getCurrencyConverted(conversionRate: ConversionRate) {
        _currencyConverted.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _currencyConverted.postValue(getCurrencyConversionRateUseCase.execute(conversionRate).data)
        }
    }

    fun getCurrencyHistory(days: Days, historyInfo: HistoryInfo) {
        _currencyHistory.value = Resource.Loading()

        Log.d("HOUSE", "HOUSE2")

        val handlerException = CoroutineExceptionHandler { _, throwable ->
            Log.d("HOUSE", "HOUSE3")
            _currencyHistory.postValue(Resource.Error(throwable.message ?: NETWORK_CALL_ERROR ))
        }
        viewModelScope.launch(handlerException) {
            Log.d("HOUSE", "HOUSE4")
            val listOfHistory = mutableListOf<HistoryResponse>()

            val aDayHistory = async {
                historyInfo.date = days.yesterday
                Log.d("HOUSE", "HOUSE35")
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }

            val twoDaysHistory = async {
                historyInfo.date = days.twoDaysAgo
                Log.d("HOUSE", "HOUSE34")
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }

            val threeDaysHistory = async {
                historyInfo.date = days.threeDaysAgo
                Log.d("HOUSE", "HOUSE33")
                getCurrencyHistoryUseCase.execute(historyInfo).data
            }

                listOfHistory.add(aDayHistory.await().data!!)

                listOfHistory.add(twoDaysHistory.await().data!!)

                listOfHistory.add(threeDaysHistory.await().data!!)
            _currencyHistory.postValue(Resource.Success(listOfHistory))
            }


        }
    }

