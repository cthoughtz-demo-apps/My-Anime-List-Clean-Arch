package com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.seasonal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentNextBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.NextSeasonAdapter
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SeasonalViewModel

class NextFragment : Fragment() {

    private var _binding: FragmentNextBinding? = null
    private val binding get() = _binding!!
    private val seasonalViewModel: SeasonalViewModel by activityViewModels()
    private val TAG = NextFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNextBinding.inflate(inflater,container,false)
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
                    Log.d(TAG,"NextFragment Seasonal $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "NextFragment Seasonal ${it.data.data}")
                    updateNextRecyclerview(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG,"NextFragment Seasonal $it")
                }
            }
        })
    }

    private fun updateNextRecyclerview(data: List<SeasonalResponse.Data>) {
        binding.apply {
            nextRecyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 2)
                adapter = NextSeasonAdapter(data)
            }
        }
    }
}