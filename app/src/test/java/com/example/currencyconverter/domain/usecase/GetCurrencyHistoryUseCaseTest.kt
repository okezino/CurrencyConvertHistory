package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.HistoryResponse
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import com.example.currencyconverter.presentation.model.ConversionRate
import com.example.currencyconverter.presentation.model.HistoryInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCurrencyHistoryUseCaseTest{
    private lateinit var mockMainCurrencyRepository: MainCurrencyRepository
    private lateinit var mockGetCurrencyHistoryUseCase: GetCurrencyHistoryUseCase
    private lateinit var mockHistoryResponse : HistoryResponse
    private lateinit var mockHistoryInfo : HistoryInfo

    @Before
    fun setup(){
        mockMainCurrencyRepository = mock()
        mockGetCurrencyHistoryUseCase = GetCurrencyHistoryUseCase(mockMainCurrencyRepository)
        mockHistoryResponse  = mock()
        mockHistoryInfo = mock()
    }

    @Test
    fun `get success on conversion history calls`() : Unit = runBlocking {
        val mockSuccess : Resource.Success<HistoryResponse> = mock()
        whenever(mockMainCurrencyRepository.getCurrencyHistory(mockHistoryInfo.date, mockHistoryInfo.base, mockHistoryInfo.symbols)).thenReturn(
            mockSuccess
        )
        val response = mockGetCurrencyHistoryUseCase.execute(mockHistoryInfo)
        assertEquals(response.data, mockSuccess)
    }

    @Test
    fun `get failed response on conversion history calls`() : Unit = runBlocking {
        val mockError : Resource.Error<HistoryResponse> = mock()
        whenever(mockMainCurrencyRepository.getCurrencyHistory(mockHistoryInfo.date, mockHistoryInfo.base, mockHistoryInfo.symbols)).thenReturn(
            mockError
        )
        val response = mockGetCurrencyHistoryUseCase.execute(mockHistoryInfo)
        assertEquals(response.data, mockError)
    }



}