package com.wizeline.heroes.core.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(
    includes = [
        UtilsModule::class,
        ServicesModule::class,
        UseCaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object CoreModule
