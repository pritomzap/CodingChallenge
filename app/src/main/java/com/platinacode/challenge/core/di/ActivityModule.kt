package com.newroztech.deshipay.service.di

import com.deshipay.common.network.ApiServices
import com.platinacode.challenge.core.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object ActivityModule {
    @Provides
    fun provideRetrofit(): Retrofit = ApiClient().getRetrofit("https://api.nobelprize.org")!!
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
}