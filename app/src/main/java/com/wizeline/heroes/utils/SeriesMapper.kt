package com.wizeline.heroes.utils

import com.wizeline.heroes.data.models.model.detail.CharacterSeriesModel
import com.wizeline.heroes.data.models.model.detail.SerieDataModel
import com.wizeline.heroes.data.models.model.home.ThumbnailModel
import com.wizeline.heroes.data.models.model.series.SerieInfoModel
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.data.models.response.detail.CharacterSeriesResponse
import com.wizeline.heroes.data.models.response.detail.SerieDataResponse
import com.wizeline.heroes.data.models.response.detail.series.SerieInfoResponse
import com.wizeline.heroes.data.models.response.detail.series.SeriesResponse
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE

object SeriesMapper {
    fun CharacterSeriesResponse.toMap() : CharacterSeriesModel {
        return CharacterSeriesModel(
            name = name ?: EMPTY_VALUE ,
            description = description,
            results = results?.map { seriesResponse ->
                com.wizeline.heroes.data.models.model.detail.SeriesModel(
                    series = seriesResponse.series?.toMap() ?: listOf(),
                    comics = seriesResponse.comics?.toMap() ?: listOf(),
                    stories = seriesResponse.stories?.toMap() ?: listOf(),
                    events = seriesResponse.stories?.toMap() ?: listOf()
                )
            } ?: listOf()
        )
    }

    fun List<SerieDataResponse>.toMap() = map {
        SerieDataModel(
            id = it.uri?.cutUriId() ?: EMPTY_VALUE,
            uri = it.uri ?: EMPTY_VALUE,
            name = it.name ?: EMPTY_VALUE,
            type = seriesTypeMapper(it.type ?: EMPTY_VALUE)
        )
    }

    private fun String.cutUriId() : String {
        return this.split("/")[this.split("/").size - 1]
    }

    private fun seriesTypeMapper(type : String) : SerieType {
        return when(type) {
            "cover" -> SerieType.Cover
            "interiorStory" -> SerieType.InteriorStory
            else -> SerieType.Cover
        }
    }
}
object SerieMapper {
    fun SeriesResponse.toMap() : SeriesModel =
        SeriesModel(
            results = this.results.toMap()
        )

    fun List<SerieInfoResponse>.toMap() = map {
        SerieInfoModel(
            id = it.id ?: EMPTY_VALUE,
            title = it.title ?: EMPTY_VALUE,
            description = it.description ?: EMPTY_VALUE,
            format = it.format ?: EMPTY_VALUE,
            thumbnail = it.thumbnail ?: ThumbnailModel( EMPTY_VALUE, EMPTY_VALUE)
        )
    }
}

enum class SerieType(type : String) {
    Cover("cover"),
    InteriorStory("interiorStory"),
}