package com.wizeline.heroes.ui.adapter.character

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.wizeline.heroes.R
import com.wizeline.heroes.data.models.model.home.ResultModel
import com.wizeline.heroes.databinding.CharacterLayoutBinding
import com.wizeline.heroes.ui.home.HomeFragmentDirections
import com.wizeline.heroes.ui.search.SearchFragmentDirections
import com.wizeline.heroes.utils.ConstVals.EMPTY_VALUE
import com.wizeline.heroes.utils.GlideUtils
import com.wizeline.heroes.utils.gone
import com.wizeline.heroes.utils.hide

class CharacterHolder(private val binding: CharacterLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun setView(data : ResultModel) = binding.apply {
        characterName.text = data.name

        if (data.description == EMPTY_VALUE)
            characterDescription.gone()

        characterDescription.text = data.description
        GlideUtils
            .getInstance(binding.root.context)
            .load("${data.thumbnail.path}.${data.thumbnail.extension}")
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.placeholder_image)
            .into(characterImg)

        characterCardView.setOnClickListener {
            with(it.findNavController()) {
                when(this.currentDestination?.id) {
                    R.id.homeFragment -> navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment2(data))
                    R.id.searchFragment2 -> navigate(SearchFragmentDirections.actionSearchFragment2ToDetailFragment2(data))
                }
            }
        }
    }
}
