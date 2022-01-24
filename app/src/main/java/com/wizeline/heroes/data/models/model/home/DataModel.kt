package com.wizeline.heroes.data.models.model.home

data class DataModel(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<ResultModel>,
)