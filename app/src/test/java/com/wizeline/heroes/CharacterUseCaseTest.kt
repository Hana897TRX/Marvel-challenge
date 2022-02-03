package com.wizeline.heroes

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.domain.repository.characters.HeroesRepository
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCase
import com.wizeline.heroes.domain.usecases.heroes.HeroesUseCaseImp
import com.wizeline.heroes.utils.DataStates
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CharacterUseCaseTest {

    private val repositoryImp: HeroesRepository = mock()
    private val heroesUseCaseImp: HeroesUseCase = HeroesUseCaseImp(repositoryImp)

    companion object {
        val ZERO_VALUE = 0
        val OFFSET_CONFIG = 20
    }

    private val response = DataModel(ZERO_VALUE, ZERO_VALUE, ZERO_VALUE, ZERO_VALUE, emptyList())

    @Test
    fun successHeroesRequest() = runBlocking {
        whenever(repositoryImp.getCharacters(null, ZERO_VALUE)).thenReturn(
            flowOf(DataStates.Success(response))
        )
        heroesUseCaseImp.invoke(ZERO_VALUE).collect {
            assertTrue(
                it is DataStates.Success
            )

            val data = it as DataStates.Success

            Assert.assertEquals(
                response,
                data.data
            )
        }
    }


    @Test
    fun successOffsetRequest() = runBlocking {
        response.offset = OFFSET_CONFIG
        whenever(repositoryImp.getCharacters(null, OFFSET_CONFIG)).thenReturn(
            flowOf(DataStates.Success(response))
        )
        heroesUseCaseImp.invoke(OFFSET_CONFIG).collect {
            val data = (it as DataStates.Success)
            Assert.assertEquals(
                response,
                data.data
            )
        }
    }
}