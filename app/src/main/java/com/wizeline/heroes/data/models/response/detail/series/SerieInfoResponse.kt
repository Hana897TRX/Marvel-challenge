package com.wizeline.heroes.data.models.response.detail.series

import com.wizeline.heroes.data.models.model.home.ThumbnailModel

data class SerieInfoResponse (
    val id : String?,
    val title : String?,
    val description : String?,
    val format : String?,
    val thumbnail : ThumbnailModel?
    )