package com.wizeline.heroes.core.di

import com.wizeline.heroes.data.network.HeroesService
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHeroesService(retrofit: Retrofit) : HeroesService =
        retrofit.create(HeroesService::class.java)
}