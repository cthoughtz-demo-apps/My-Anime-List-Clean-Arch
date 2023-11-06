package com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.all

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentSearchedAnimeBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.SearchedAdapter
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SearchViewModel

class SearchedAnimeFragment : Fragment() {

    var binding: FragmentSearchedAnimeBinding? = null
    private val searchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchedAnimeBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
    }



    private fun addObservers() {
        searchViewModel.animeSearch.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    Log.d("DiscoverySearchAnime",it.data.data.toString())
                    setupUI(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d("DiscoverySearchAnime",it.errorMessage)
                    Toast.makeText(requireContext(), "Error: ${it.errorMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupUI(data: List<AnimeSearchResponse.Data>) {
        binding?.apply {
            searchedAnimeRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

                adapter = SearchedAdapter(data,getString(R.string.searched_anime_fragment))
                Log.d("DataInfo",data.toString())
            }
        }
    }
}