package com.wizeline.heroes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.network.HeroesService
import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSource
import com.wizeline.heroes.data.remote.characters.HeroesRemoteDataSourceImp
import com.wizeline.heroes.domain.repository.characters.HeroesRepository
import com.wizeline.heroes.domain.repository.characters.HeroesRepositoryImp
import com.wizeline.heroes.domain.usecases.search.SearchUseCase
import com.wizeline.heroes.domain.usecases.search.SearchUseCaseImp
import com.wizeline.heroes.ui.search.SearchFragmentViewModel
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.Network
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchFragmentViewModel

    private val searchUseCase: SearchUseCase = mock()

    private var DATA_MODEL = DataModel(0, 0, 0, 0, emptyList())

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)
        searchViewModel = SearchFragmentViewModel(searchUseCase, coroutineDispatcher)
    }

    companion object {
        val EMPTY_STRING = ""
        val ZERO_VALUE = 0
        val ERROR_MESSAGE = "Error"
        val ERROR_CODE = 502
        val OFFSET_CONFIG = 20
    }

    @Test
    fun fetch_search_characters_with_dataState_succefull() = coroutineDispatcher.runBlockingTest {
        whenever(searchUseCase.searchCharacters(EMPTY_STRING, ZERO_VALUE)).thenReturn(
            flowOf(DataStates.Success(DATA_MODEL))
        )
        searchViewModel.getCharacters()
        coroutineDispatcher.advanceUntilIdle()
        val data = searchViewModel.characters.value

        assertTrue(
            data is DataStates.Success
        )

        val result = data as DataStates.Success

        assertTrue(
            result.data != null
        )
    }

    @Test
    fun fetch_search_characters_with_dataState_error() = coroutineDispatcher.runBlockingTest {
        whenever(searchUseCase.searchCharacters(EMPTY_STRING, ZERO_VALUE)).thenReturn(
            flowOf(DataStates.Error(ERROR_CODE, ERROR_MESSAGE))
        )
        searchViewModel.getCharacters()
        coroutineDispatcher.advanceUntilIdle()
        val data = searchViewModel.characters.value

        assertTrue(
            data is DataStates.Error
        )

        val result = data as DataStates.Error

        assertTrue(
            result.errorMessage.isNotEmpty()
        )

        assertTrue(
            result.code > ZERO_VALUE
        )
    }

    @Test
    fun fetch_search_characters_with_offset_20() = coroutineDispatcher.runBlockingTest {
        DATA_MODEL.offset = OFFSET_CONFIG
        whenever(searchUseCase.searchCharacters(EMPTY_STRING, OFFSET_CONFIG)).thenReturn(
            flowOf(DataStates.Success(DATA_MODEL))
        )
        searchViewModel.nextPage()
        coroutineDispatcher.advanceUntilIdle()
        val data = searchViewModel.characters.value
        assertTrue(
            data is DataStates.Success
        )
        val response = data as DataStates.Success

        Assert.assertEquals(
            DATA_MODEL,
            response.data
        )
    }

    @After
    fun shutDown() {
        Dispatchers.resetMain()
    }
}