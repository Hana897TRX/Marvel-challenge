package com.wizeline.heroes.network

import com.wizeline.heroes.Characters
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("characters")
    fun characters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Response<Characters>
}