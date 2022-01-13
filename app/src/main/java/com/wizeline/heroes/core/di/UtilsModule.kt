package com.wizeline.heroes.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com:443/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}