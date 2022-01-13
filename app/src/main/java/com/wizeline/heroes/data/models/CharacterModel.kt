package com.wizeline.heroes.data.models

data class CharacterModel(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: DataModel
)
