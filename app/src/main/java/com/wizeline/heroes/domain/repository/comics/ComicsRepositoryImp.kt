package com.wizeline.heroes.domain.repository.comics

import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.data.remote.comics.ComicsRemoteDataSource
import com.wizeline.heroes.domain.repository.series.SeriesRepository
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicsRepositoryImp @Inject constructor(
    private val comicsRemoteDataSource: ComicsRemoteDataSource
) : ComicsRepository {
    override suspend fun getComics(characterId: String): Flow<DataStates<SeriesModel>> =
        comicsRemoteDataSource.getComics(characterId)
}