package com.wizeline.heroes.data.models.model.home

data class CharacterModel(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataModel
)
