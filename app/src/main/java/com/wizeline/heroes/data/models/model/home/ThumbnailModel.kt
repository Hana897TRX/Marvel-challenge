package com.wizeline.heroes.data.models.model.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ThumbnailModel (
    val path: String,
    val extension: String
) : Parcelable
