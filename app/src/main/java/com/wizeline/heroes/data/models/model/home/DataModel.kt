package com.wizeline.heroes.data.models.model.home

data class DataModel(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: List<ResultModel>,
)