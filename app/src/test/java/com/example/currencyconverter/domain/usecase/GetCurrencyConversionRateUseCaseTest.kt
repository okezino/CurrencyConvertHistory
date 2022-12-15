package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import com.example.currencyconverter.presentation.model.ConversionRate
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCurrencyConversionRateUseCaseTest{
    private lateinit var mockMainCurrencyRepository: MainCurrencyRepository
    private lateinit var mockGetCurrencyConversionRateUseCase: GetCurrencyConversionRateUseCase
    private lateinit var mockConversionRate : ConversionRate

    @Before
    fun setup(){
        mockMainCurrencyRepository = mock()
        mockGetCurrencyConversionRateUseCase = GetCurrencyConversionRateUseCase(mockMainCurrencyRepository)
        mockConversionRate = mock()
    }

    @Test
    fun `get success on conversion rate calls`() : Unit = runBlocking {
        val mockSuccess : Resource.Success<CurrencyConversionResponse> = mock()
        whenever(mockMainCurrencyRepository.getCurrencyConversionRate(mockConversionRate)).thenReturn(
            mockSuccess
        )
        val response = mockGetCurrencyConversionRateUseCase.execute(mockConversionRate)
        assertEquals(response.data, mockSuccess)
    }

    @Test
    fun `get failed response on conversion rate calls`() : Unit = runBlocking {
        val mockError : Resource.Error<CurrencyConversionResponse> = mock()
        whenever(mockMainCurrencyRepository.getCurrencyConversionRate(mockConversionRate)).thenReturn(
            mockError
        )
        val response = mockGetCurrencyConversionRateUseCase.execute(mockConversionRate)
        assertEquals(response.data, mockError)
    }
}