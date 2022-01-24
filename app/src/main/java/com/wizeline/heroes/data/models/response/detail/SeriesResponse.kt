package com.wizeline.heroes.data.models.response.detail

data class SeriesResponse (
    val comics : List<SerieDataResponse>,
    val series : List<SerieDataResponse>,
    val stories : List<SerieDataResponse>,
    val events : List<SerieDataResponse>
    )