package com.wizeline.heroes.core.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.wizeline.heroes.data.network.HeroesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Singleton
    @Provides
    fun provideHeroesService(retrofit: Retrofit) : HeroesService =
        retrofit.create(HeroesService::class.java)
}