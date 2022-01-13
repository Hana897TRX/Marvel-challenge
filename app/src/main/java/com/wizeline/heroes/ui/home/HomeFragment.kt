package com.wizeline.heroes.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.wizeline.heroes.data.models.Characters
import com.wizeline.heroes.databinding.FragmentHomeBinding
import com.wizeline.heroes.makeToast
import com.wizeline.heroes.utils.DataStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCharactersObservable()
    }

    private fun setCharactersObservable() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.heroesUIState.collect { response ->
                when(response) {
                    is DataStates.Error -> makeToast(requireContext(), "${response.code} ${response.errorMessage}")
                    is DataStates.Loading -> Unit
                    is DataStates.Success -> setCharacters(response.data)
                }
            }
        }
    }

    private fun setCharacters(data : Characters) = binding.apply {

    }
}