package com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.all

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentSearchedMangaBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.SearchedAdapter
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SearchViewModel

class SearchedMangaFragment : Fragment() {

    var binding: FragmentSearchedMangaBinding? = null
    private val searchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchedMangaBinding.inflate(inflater,container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()

    }



    private fun addObservers() {

        searchViewModel.mangaSearch.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {}

                is NetworkResult.Success -> {
                    Log.d("DiscoverySearchManga",it.data.data.toString())
                    setupUI(it.data.data)

                }

                is NetworkResult.Failure -> {}
            }
        })
    }

    private fun setupUI(data: List<MangaSearchResponse.Data>) {
        binding?.apply {
            searchedMangaRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

                adapter = SearchedAdapter(data,getString(R.string.searched_manga_fragment))
            }
        }
    }
}