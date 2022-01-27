package com.wizeline.heroes.ui.adapter.Series

import androidx.recyclerview.widget.RecyclerView
import com.wizeline.heroes.R
import com.wizeline.heroes.data.models.model.detail.SerieDataModel
import com.wizeline.heroes.data.models.model.detail.SeriesModel
import com.wizeline.heroes.data.models.model.series.SerieInfoModel
import com.wizeline.heroes.databinding.DetailSeriesItemBinding
import com.wizeline.heroes.utils.GlideUtils

class SeriesDataHolder(private val binding: DetailSeriesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setView(serie: SerieInfoModel) = binding.apply {
        GlideUtils
            .getInstance(this.root.context)
            .load("${serie.thumbnail.path}.${serie.thumbnail.extension}")
            .placeholder(R.drawable.placeholder_image)
            .into(detailImg)
    }
}
