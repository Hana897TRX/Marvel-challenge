package com.wizeline.heroes.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wizeline.heroes.R
import com.wizeline.heroes.data.models.model.series.SeriesModel
import com.wizeline.heroes.databinding.FragmentDetailBinding
import com.wizeline.heroes.ui.adapter.Series.SeriesDataAdapter
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.GlideUtils
import com.wizeline.heroes.utils.hide
import com.wizeline.heroes.utils.show
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
                is DataStates.Error -> setInvisibleProgress()
                is DataStates.Loading -> Unit
                is DataStates.Success -> comicsDataAdapter.submitList((pair.first as DataStates.Success<SeriesModel>).data.results)
            }

            when (pair.second) {
                is DataStates.Error -> setInvisibleProgress()
                is DataStates.Loading -> Unit
                is DataStates.Success -> {
                    setInvisibleProgress()
                    setVisibleRV()
                    seriesDataAdapter.submitList((pair.second as DataStates.Success<SeriesModel>).data.results)
                }
            }
        }
    }

    private fun setInvisibleProgress() = binding.apply {
        comicsProgressBar.hide()
        seriesProgressBar.hide()
    }

    private fun setVisibleRV() = binding.apply {
        detailComicsRv.show()
        detailSeriesRv.show()
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
            detailCharacterDescription.text = it.description
        }
    }
}