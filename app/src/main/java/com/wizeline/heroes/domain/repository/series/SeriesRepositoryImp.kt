package com.wizeline.heroes.domain.repository.series

import com.wizeline.heroes.data.models.model.detail.CharacterSeriesModel
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.data.remote.series.SeriesRemoteDataSource
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeriesRepositoryImp @Inject constructor(
    private val seriesDataSource: SeriesRemoteDataSource
) : SeriesRepository {
    override suspend fun getSeries(characterId: String): Flow<DataStates<SeriesModel>> = withContext(Dispatchers.IO) {
        seriesDataSource.getSeries(characterId)
    }
}