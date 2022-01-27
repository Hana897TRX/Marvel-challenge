package com.wizeline.heroes.domain.usecases.comics

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.domain.repository.comics.ComicsRepository
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicsUseCaseImp @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ComicsUseCase {
    override suspend fun getComics(characterId: String): Flow<DataStates<SeriesModel>> =
        comicsRepository.getComics(characterId)
}