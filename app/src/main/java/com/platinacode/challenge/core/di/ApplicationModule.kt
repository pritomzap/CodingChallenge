package com.newroztech.deshipay.service.di

import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule{

    @Singleton
    @Provides
    fun provideLocationManager(@ApplicationContext appContext: Context):LocationManager = appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext appContext: Context):Context = appContext
}