package com.wizeline.heroes.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.wizeline.heroes.databinding.FragmentHomeBinding
import com.wizeline.heroes.makeToast
import com.wizeline.heroes.ui.adapter.CharactersAdapter
import com.wizeline.heroes.utils.DataStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : HomeFragmentViewModel by viewModels()
    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.heroesUIState.collect { response ->
                when(response) {
                    is DataStates.Error -> makeToast(requireContext(), "${response.code} ${response.errorMessage}")
                    is DataStates.Loading -> Unit // TODO ANIMATION
                    is DataStates.Success -> charactersAdapter.submitList(response.data.data.results)
                }
            }
        }
    }

    private fun offsetObservable() = lifecycleScope.launch {
        viewModel.offset.observe(viewLifecycleOwner, {
            binding.homeTextOffset.setText(it.toString())
            viewModel.getCharacters(it)
        })
    }

    private fun addViewFunctions() = binding.apply{
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