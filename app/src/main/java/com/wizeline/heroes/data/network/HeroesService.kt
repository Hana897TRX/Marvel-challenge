package com.wizeline.heroes.data.network

import com.wizeline.heroes.data.models.model.home.DataModel
import com.wizeline.heroes.data.models.model.home.MarvelResponse
import com.wizeline.heroes.data.models.response.detail.series.SeriesResponse
import com.wizeline.heroes.utils.Network.QUERY_API_KEY
import com.wizeline.heroes.utils.Network.QUERY_CHARACTER_ID
import com.wizeline.heroes.utils.Network.QUERY_HASH
import com.wizeline.heroes.utils.Network.QUERY_OFFSET
import com.wizeline.heroes.utils.Network.QUERY_TS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesService {
    @GET("characters")
    suspend fun characters(
        @Query(QUERY_OFFSET) offset : Int,
        @Query(QUERY_TS) ts: String,
        @Query(QUERY_API_KEY) apikey: String,
        @Query(QUERY_HASH) hash: String,
    ): Response<MarvelResponse<DataModel>>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path(QUERY_CHARACTER_ID) characterId : String,
        @Query(QUERY_TS) ts: String,
        @Query(QUERY_API_KEY) apikey: String,
        @Query(QUERY_HASH) hash: String,
    ) : Response<MarvelResponse<SeriesResponse>>

    @GET("characters/{characterId}/series")
    suspend fun getSeries(
        @Path(QUERY_CHARACTER_ID) characterId : String,
        @Query(QUERY_TS) ts: String,
        @Query(QUERY_API_KEY) apikey: String,
        @Query(QUERY_HASH) hash: String,
    ) : Response<MarvelResponse<SeriesResponse>>
}