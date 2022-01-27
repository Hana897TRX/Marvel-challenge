package com.wizeline.heroes.core.di

import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSource
import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSourceImp
import com.wizeline.heroes.data.remote.comics.ComicsRemoteDataSource
import com.wizeline.heroes.data.remote.comics.ComicsRemoteDataSourceImp
import com.wizeline.heroes.data.remote.series.SeriesRemoteDataSource
import com.wizeline.heroes.data.remote.series.SeriesRemoteDataSourceImp
import com.wizeline.heroes.domain.repository.characters.HeroesRepository
import com.wizeline.heroes.domain.repository.characters.HeroesRepositoryImp
import com.wizeline.heroes.domain.repository.series.SeriesRepository
import com.wizeline.heroes.domain.repository.series.SeriesRepositoryImp
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

    @Binds
    abstract fun bindSeriesDataSource(
        impl : SeriesRemoteDataSourceImp
    ) : SeriesRemoteDataSource

    @Binds
    abstract fun bindSeriesRepository(
        impl: SeriesRepositoryImp
    ) : SeriesRepository

    @Binds
    abstract fun bindComicDataSource(
        impl : ComicsRemoteDataSourceImp
    ) : ComicsRemoteDataSource
}