package com.wizeline.heroes.domain.usecases.heroes

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.utils.DataStates
import kotlinx.coroutines.flow.Flow

interface HeroesUseCase {
    suspend fun invoke(offset : Int) : Flow<DataStates<DataModel>>
}