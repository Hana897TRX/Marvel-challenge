package com.wizeline.heroes.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wizeline.heroes.R
import com.wizeline.heroes.databinding.FragmentHomeBinding
import com.wizeline.heroes.databinding.FragmentSearchFragmentBinding
import com.wizeline.heroes.ui.adapter.character.CharactersAdapter
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.hide
import com.wizeline.heroes.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel : SearchFragmentViewModel by viewModels()

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchInput()
        setView()
        observeCharacters()
        offsetObservable()
        addViewFunctions()
    }

    private fun observeSearchInput() = binding.apply {
        searchTextInput.doOnTextChanged { text, _, _, _ ->
            viewModel.searchText(text.toString())
        }
    }

    private fun setView() = binding.apply {
        searchCharactersRv.apply {
            adapter = charactersAdapter
        }
    }

    private fun observeCharacters() = lifecycleScope.launch {
        viewModel.characters.collect { response ->
            when(response) {
                is DataStates.Error -> binding.apply {
                    searchCharactersProgress.hide()
                    searchCharactersRv.hide()
                }
                is DataStates.Loading -> binding.apply {
                    searchCharactersProgress.show()
                    searchCharactersRv.hide()
                }
                is DataStates.Success -> binding.apply {
                    searchCharactersProgress.hide()
                    searchCharactersRv.show()
                    charactersAdapter.submitList(response.data.results)
                }
            }
        }
    }

    private fun offsetObservable() = lifecycleScope.launch {
        viewModel.offset.collect {
            binding.homeTextOffset.setText(it.toString())
        }
    }

    private fun addViewFunctions() = binding.apply {
        homeButtonLeft.setOnClickListener { viewModel.previousPage() }
        homeButtonRight.setOnClickListener { viewModel.nextPage() }
        homeTextOffset.doOnTextChanged { text, _, _, _ ->
            viewModel.setCustomOffset(text)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}