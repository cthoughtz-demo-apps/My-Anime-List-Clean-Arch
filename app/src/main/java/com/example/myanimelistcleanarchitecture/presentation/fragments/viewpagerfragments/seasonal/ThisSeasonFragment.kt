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
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentThisSeasonBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.ThisSeasonAdapter
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SeasonalViewModel

class ThisSeasonFragment : Fragment() {

    private var _binding: FragmentThisSeasonBinding? = null
    private val binding get() = _binding!!
    private val seasonalViewModel: SeasonalViewModel by activityViewModels()
    private val TAG = ThisSeasonFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThisSeasonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
    }

   private fun  addObservers() {
           seasonalViewModel.seasonal.observe(viewLifecycleOwner, Observer {
               when(it) {

                   is NetworkResult.Loading -> {
                       Log.d(TAG, "ThisSeason Seasonal Loading ${it}")
                   }

                   is NetworkResult.Success -> {
                       Log.d(TAG, "ThisSeason Success ${it.data.data}")
                       updateThisSeasonalRecyclerView(it.data.data)
                   }

                   is NetworkResult.Failure -> {
                       Log.d(TAG, "ThisSeason Seasonal Failure $it")
                   }
               }
           })
       }

    private fun updateThisSeasonalRecyclerView(data: List<SeasonalResponse.Data>) {
        binding.apply {
            thisSeasonalRecyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(),2)
                adapter = ThisSeasonAdapter(data)
            }
        }
    }
}