package com.wizeline.heroes.domain.usecases

import com.wizeline.heroes.domain.repository.series.SeriesRepository
import javax.inject.Inject

class SeriesUseCase @Inject constructor(
    private val repository : SeriesRepository
){
    suspend operator fun invoke(characterId : String) =
        repository.getSeries(characterId)
}