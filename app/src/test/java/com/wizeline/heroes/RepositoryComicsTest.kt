package com.wizeline.heroes

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.data.remote.comics.ComicsRemoteDataSource
import com.wizeline.heroes.domain.repository.comics.ComicsRepository
import com.wizeline.heroes.domain.repository.comics.ComicsRepositoryImp
import com.wizeline.heroes.utils.DataStates
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RepositoryComicsTest {

    companion object {
        val EMPTY_STRING = ""
        val ERROR_MESSAGE = "Error"
        val ERROR_CODE = 502
    }

    private val comicsRemoteDataSource: ComicsRemoteDataSource = mock()

    private val comicsRepository: ComicsRepository = ComicsRepositoryImp(comicsRemoteDataSource)

    private val response = SeriesModel(emptyList())

    @Test
    fun successNoDataRequest() = runBlocking {
        whenever(comicsRemoteDataSource.getComics(EMPTY_STRING)).thenReturn(
            flowOf(DataStates.Success(response))
        )

        comicsRepository.getComics(EMPTY_STRING).collect {
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
    fun errorRequest() = runBlocking {
        whenever(comicsRemoteDataSource.getComics(EMPTY_STRING)).thenReturn(
            flowOf(DataStates.Error(ERROR_CODE, ERROR_MESSAGE))
        )

        comicsRepository.getComics(EMPTY_STRING).collect {
            assertTrue(
                it is DataStates.Error
            )

            val data = it as DataStates.Error

            Assert.assertEquals(
                data.code,
                ERROR_CODE
            )

            Assert.assertEquals(
                data.errorMessage,
                ERROR_MESSAGE
            )
        }
    }
}