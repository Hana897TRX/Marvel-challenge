package com.wizeline.heroes.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.wizeline.heroes.R
import com.wizeline.heroes.data.models.ResultModel
import com.wizeline.heroes.databinding.CharacterLayoutBinding
import com.wizeline.heroes.utils.GlideUtils
import dagger.internal.InjectedFieldSignature
import javax.inject.Inject

class CharacterHolder(private val binding: CharacterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setView(data : ResultModel) = binding.apply {
        characterName.text = data.name
        characterDescription.text = data.description
        GlideUtils
            .getInstance(binding.root.context)
            .load("${data.thumbnail.path}.${data.thumbnail.extension}")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.placeholder_image)
            .into(characterImg)
    }
}