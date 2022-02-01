package com.wizeline.heroes.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wizeline.heroes.R
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.databinding.FragmentDetailBinding
import com.wizeline.heroes.ui.adapter.Series.SeriesDataAdapter
import com.wizeline.heroes.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailFragmentViewModel by viewModels()

    private val seriesDataAdapter = SeriesDataAdapter()
    private val comicsDataAdapter = SeriesDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        viewModel.character = args.characterData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        setView()
        setData()
    }

    private fun setData() = lifecycleScope.launch {
        viewModel.comicsUiState.zip(viewModel.seriesUiState) { comics, series ->
            Pair(comics, series)
        }.collect { pair ->
            when (pair.first) {
                is DataStates.Error -> binding.apply {
                    detailDataProgress.hide()
                }
                is DataStates.Loading -> Unit
                is DataStates.Success -> binding.apply {
                    if((pair.first as DataStates.Success<SeriesModel>).data.results.isNotEmpty()) {
                        cardComics.show()
                        detailComicsRv.show()
                    }
                    detailDataProgress.hide()
                    comicsDataAdapter.submitList((pair.first as DataStates.Success<SeriesModel>).data.results)
                }
            }

            when (pair.second) {
                is DataStates.Error -> binding.apply {
                    detailDataProgress.hide()
                }
                is DataStates.Loading -> Unit
                is DataStates.Success -> binding.apply {
                    if((pair.second as DataStates.Success<SeriesModel>).data.results.isNotEmpty()) {
                        cardSeries.show()
                        detailSeriesRv.show()
                    }
                    detailDataProgress.hide()
                    seriesDataAdapter.submitList((pair.second as DataStates.Success<SeriesModel>).data.results)
                }
            }
        }
    }

    private fun setView() = binding.apply {
        detailSeriesRv.apply {
            adapter = seriesDataAdapter
        }
        detailComicsRv.apply {
            adapter = comicsDataAdapter
        }

        viewModel.character?.let {
            characterName.text = it.name

            GlideUtils
                .getInstance(requireContext())
                .load("${it.thumbnail.path}.${it.thumbnail.extension}")
                .placeholder(R.drawable.placeholder_image)
                .into(detailCharacterImage)

            if(it.description.isBlank()) {
                detailCharacterDescription.gone()
            }
            else {
                detailCharacterDescription.text = it.description
            }
        }

        detailBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}