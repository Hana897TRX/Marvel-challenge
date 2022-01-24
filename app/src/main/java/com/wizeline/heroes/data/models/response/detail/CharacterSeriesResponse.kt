package com.wizeline.heroes.data.models.response.detail

data class CharacterSeriesResponse (
    val name : String,
    val description : String?,
    val results : List<SeriesResponse>?
)