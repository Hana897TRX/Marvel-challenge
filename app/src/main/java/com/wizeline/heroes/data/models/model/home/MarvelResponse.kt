package com.wizeline.heroes.data.models.model.home

data class MarvelResponse<out T>(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: T
)
