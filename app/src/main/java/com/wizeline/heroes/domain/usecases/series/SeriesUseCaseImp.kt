package com.wizeline.heroes.domain.usecases.series

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.domain.repository.series.SeriesRepository
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeriesUseCaseImp @Inject constructor(
    private val repository : SeriesRepository
) : SeriesUseCase {
    override suspend fun getSeries(characterId : String) =
        repository.getSeries(characterId)
}