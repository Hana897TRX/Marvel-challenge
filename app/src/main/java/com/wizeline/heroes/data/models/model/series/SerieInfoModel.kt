package com.wizeline.heroes.data.models.model.series

import com.wizeline.heroes.data.models.model.home.ThumbnailModel

data class SerieInfoModel (
    val id : String,
    val title : String,
    val description : String,
    val format : String,
    val thumbnail : ThumbnailModel
)