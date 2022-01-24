package com.wizeline.heroes.data.network

import com.wizeline.heroes.data.models.model.home.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("characters")
    suspend fun characters(
        @Query("offset") offset : Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Response<CharacterModel>
}