package com.wizeline.heroes.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wizeline.heroes.databinding.FragmentHomeBinding
<<<<<<< HEAD
import com.wizeline.heroes.utils.makeToast
=======
import com.wizeline.heroes.makeToast
>>>>>>> a452a6a (Feature: Detail service working)
import com.wizeline.heroes.ui.adapter.character.CharactersAdapter
import com.wizeline.heroes.utils.DataStates
import com.wizeline.heroes.utils.hide
import com.wizeline.heroes.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCharacters()
        setCharactersObservable()
        addViewFunctions()
        offsetObservable()
    }

    private fun setCharactersObservable() = lifecycleScope.launch {
        viewModel.heroesUIState.collect { response ->
            when (response) {
                is DataStates.Error -> {
                    makeToast(
                        requireContext(),
                        "${response.code} ${response.errorMessage}"
                    )
<<<<<<< HEAD
                    binding.homeCharacterProgressBar.hide()
=======
                    binding.homeCharacterProgressBar.visibility = View.INVISIBLE
>>>>>>> a452a6a (Feature: Detail service working)
                }
                is DataStates.Loading -> Unit
                is DataStates.Success -> {
                    binding.apply {
<<<<<<< HEAD
                        homeCharacterProgressBar.hide()
                        homeCharactersRv.show()
=======
                        homeCharacterProgressBar.visibility = View.INVISIBLE
                        homeCharactersRv.visibility = View.VISIBLE
>>>>>>> a452a6a (Feature: Detail service working)
                    }
                    charactersAdapter.submitList(response.data.results)
                }
            }
        }
    }

    private fun offsetObservable() = lifecycleScope.launch {
        viewModel.offset.debounce(200).collect {
            binding.homeTextOffset.setText(it.toString())
            viewModel.getCharacters(it)
        }
    }

    private fun addViewFunctions() = binding.apply {
        homeButtonLeft.setOnClickListener { viewModel.previousPage() }
        homeButtonRight.setOnClickListener { viewModel.nextPage() }
        homeTextOffset.doOnTextChanged { text, _, _, _ ->
            viewModel.setCustomOffset(text)
        }
    }

    private fun setCharacters() = binding.apply {
        homeCharactersRv.apply {
            adapter = charactersAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}