package com.example.currencyconverter.domain.usecase

import com.example.currencyconverter.common.Resource
import com.example.currencyconverter.data.model.CurrencyConversionResponse
import com.example.currencyconverter.data.model.SymbolResult
import com.example.currencyconverter.domain.repository.MainCurrencyRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCurrencySymbolsUseCaseTest{
    private lateinit var mockMainCurrencyRepository: MainCurrencyRepository
    private lateinit var mockSymbolResult  : SymbolResult
    private lateinit var mockGetCurrencySymbolsUseCase: GetCurrencySymbolsUseCase



    @Before
    fun setup(){
        mockMainCurrencyRepository = mock()
        mockGetCurrencySymbolsUseCase= GetCurrencySymbolsUseCase(mockMainCurrencyRepository)
        mockSymbolResult = mock()
    }

    @Test
    fun `get success on conversion symbol calls`() : Unit = runBlocking {
        val mockSuccess : Resource.Success<SymbolResult> = mock()
        whenever(mockMainCurrencyRepository.getCurrencySymbols()).thenReturn(
            mockSuccess
        )
        val response = mockGetCurrencySymbolsUseCase.execute(Unit)
        assertEquals(response.data, mockSuccess)
    }

    @Test
    fun `get failed response on conversion symbol calls`() : Unit = runBlocking {
        val mockError : Resource.Error<SymbolResult> = mock()
        whenever(mockMainCurrencyRepository.getCurrencySymbols()).thenReturn(
            mockError
        )
        val response = mockGetCurrencySymbolsUseCase.execute(Unit)
        assertEquals(response.data, mockError)
    }

}