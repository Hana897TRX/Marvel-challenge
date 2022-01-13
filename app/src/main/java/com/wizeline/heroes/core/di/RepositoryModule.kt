package com.wizeline.heroes.core.di

import com.wizeline.heroes.data.remote.HeroesRemoteDataSource
import com.wizeline.heroes.data.remote.HeroesRemoteDataSourceImp
import com.wizeline.heroes.domain.repository.HeroesRepository
import com.wizeline.heroes.domain.repository.HeroesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHeroesRemoteDataSource(
        impl: HeroesRemoteDataSourceImp
    ) : HeroesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindHeroesRepository(
        impl: HeroesRepositoryImp
    ) : HeroesRepository
}