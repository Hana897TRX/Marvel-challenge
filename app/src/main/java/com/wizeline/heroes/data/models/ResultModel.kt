package com.wizeline.heroes.data.models

data class ResultModel(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailModel,
    val resourceURI: String
)