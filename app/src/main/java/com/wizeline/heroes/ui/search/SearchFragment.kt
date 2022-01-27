package com.wizeline.heroes.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wizeline.heroes.R
import com.wizeline.heroes.databinding.FragmentHomeBinding
import com.wizeline.heroes.databinding.FragmentSearchFragmentBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}