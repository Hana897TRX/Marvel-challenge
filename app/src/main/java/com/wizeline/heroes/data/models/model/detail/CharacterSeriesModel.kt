package com.wizeline.heroes.data.models.model.detail

data class CharacterSeriesModel (
    val name : String,
    val description : String?,
    val results : List<SeriesModel>
)