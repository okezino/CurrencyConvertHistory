package com.example.currencyconverter.di

import com.example.currencyconverter.common.NetworkUtil.BASE_URL
import com.example.currencyconverter.data.network.service.CurrencyConverterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideCurrencyConverterApiService(interceptor: Interceptor): CurrencyConverterService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client( OkHttpClient.Builder()
                .addInterceptor(interceptor).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyConverterService::class.java)
    }

}