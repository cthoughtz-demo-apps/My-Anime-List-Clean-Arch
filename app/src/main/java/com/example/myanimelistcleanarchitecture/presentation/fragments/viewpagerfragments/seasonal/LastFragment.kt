package com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.seasonal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentLastBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.LastSeasonAdapter
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SeasonalViewModel

class LastFragment : Fragment() {

    private var _binding: FragmentLastBinding? = null
    private val binding get() = _binding!!
    private val seasonalViewModel: SeasonalViewModel by activityViewModels()
    private val TAG = ListFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
    }

    private fun addObservers() {
        seasonalViewModel.seasonal.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Last Fragment Loading $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "Last Fragment Success ${it.data.data}")
                    updateLastRecyclerView(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG, "Last Fragment Failure $it")
                }
            }
        })
    }

    private fun updateLastRecyclerView(data: List<SeasonalResponse.Data>) {
        binding.apply {
            lastRecyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 2)
                adapter = LastSeasonAdapter(data)
            }
        }
    }
}