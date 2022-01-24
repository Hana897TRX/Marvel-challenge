package com.wizeline.heroes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.data.models.model.detail.SerieDataModel
import com.wizeline.heroes.databinding.DetailSeriesItemBinding

class SeriesDataAdapter : ListAdapter<SerieDataModel, SeriesDataHolder>(DiffUtilCallback()) {
    private class DiffUtilCallback : DiffUtil.ItemCallback<SerieDataModel>() {
        override fun areItemsTheSame(oldItem: SerieDataModel, newItem: SerieDataModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SerieDataModel, newItem: SerieDataModel) =
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
