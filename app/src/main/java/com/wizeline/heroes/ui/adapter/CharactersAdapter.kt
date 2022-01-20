package com.wizeline.heroes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.wizeline.heroes.data.models.ResultModel
import com.wizeline.heroes.databinding.CharacterLayoutBinding
import javax.inject.Inject

class CharactersAdapter : ListAdapter<ResultModel, CharacterHolder>(DiffUtilCallback()) {
    private class DiffUtilCallback : DiffUtil.ItemCallback<ResultModel>() {
        override fun areItemsTheSame(oldItem: ResultModel, newItem: ResultModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ResultModel, newItem: ResultModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterHolder(
            CharacterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = getItem(position)
        holder.setView(character)
    }
}