package com.wizeline.heroes.core.di

import com.wizeline.heroes.utils.Network
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
            .baseUrl(Network.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}