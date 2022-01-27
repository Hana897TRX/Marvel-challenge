package com.wizeline.heroes.ui.adapter.Series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.data.models.model.detail.SerieDataModel
import com.wizeline.heroes.data.models.model.series.SerieInfoModel
import com.wizeline.heroes.databinding.DetailSeriesItemBinding

class SeriesDataAdapter : ListAdapter<SerieInfoModel, SeriesDataHolder>(DiffUtilCallback()) {
    private class DiffUtilCallback : DiffUtil.ItemCallback<SerieInfoModel>() {
        override fun areItemsTheSame(oldItem: SerieInfoModel, newItem: SerieInfoModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SerieInfoModel, newItem: SerieInfoModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SeriesDataHolder(
        DetailSeriesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SeriesDataHolder, position: Int) {
        val serie = getItem(position)
        holder.setView(serie)
    }
}
