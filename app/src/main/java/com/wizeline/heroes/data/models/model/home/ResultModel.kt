package com.wizeline.heroes.data.models.model.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailModel,
    val resourceURI: String
) : Parcelable