package com.wizeline.heroes.data.models.model.detail

import com.wizeline.heroes.utils.SerieType

data class SerieDataModel (
    val id : String,
    val uri : String,
    val name : String,
    val type : SerieType
    )