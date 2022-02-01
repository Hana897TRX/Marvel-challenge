package com.wizeline.heroes.core.di

import com.wizeline.heroes.domain.usecases.comics.ComicsUseCase
import com.wizeline.heroes.domain.usecases.comics.ComicsUseCaseImp
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCase
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCaseImp
import com.wizeline.heroes.domain.usecases.search.SearchUseCase
import com.wizeline.heroes.domain.usecases.search.SearchUseCaseImp
import com.wizeline.heroes.domain.usecases.series.SeriesUseCase
import com.wizeline.heroes.domain.usecases.series.SeriesUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun bindHeroesUseCase(
        impl: HeroesUseCaseImp
    ) : HeroesUseCase

    @Binds
    abstract fun bindSeriesUseCase(
        impl: SeriesUseCaseImp
    ) : SeriesUseCase

    @Binds
    abstract fun bindComicsUseCase(
        impl: ComicsUseCaseImp
    ) : ComicsUseCase

    @Binds
    abstract fun bindSearchUseCase(
        impl : SearchUseCaseImp
    ) : SearchUseCase
}