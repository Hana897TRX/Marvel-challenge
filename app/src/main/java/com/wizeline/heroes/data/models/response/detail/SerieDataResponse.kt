package com.wizeline.heroes.data.models.response.detail

import com.google.gson.annotations.SerializedName

data class SerieDataResponse (
    @SerializedName("resourceURI")
    val uri : String?,
    val name : String?,
    val type : String?
    )