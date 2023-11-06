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
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentSearchedAllBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SearchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchedAllFragment : Fragment() {

    var _binding: FragmentSearchedAllBinding? = null
    private val binding get() = _binding!!
   // private val searchViewModel: SearchViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchedAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  addObservers()
    }


 //   private fun addObservers() {

//        searchViewModel.allSearch.observe(viewLifecycleOwner, Observer {
//
//            var anime: List<AnimeSearchResponse.Data>? = null
//            var manga: List<MangaSearchResponse.Data>? = null
//
//            when(it.first.value) {
//
//                is NetworkResult.Loading -> {
//
//                }
//
//                is NetworkResult.Success -> {
//                  //  anime = (it.first.value as NetworkResult.Success<AnimeSearchResponse>).data.data
//                  //  Log.d("SearchedResultsAni",anime.toString())
//                }
//
//                is NetworkResult.Failure -> {
//                   // var error = (it.first.value as NetworkResult.Failure<AnimeSearchResponse>).errorMessage
//                    // Todo --> Note I am getting an error when calling both anime and manga at the same time due to the amount of request that can be made within 3 seconds. Sometimes both calls come through but most of the time I get a response that says to many request have been made. Nothing I can do about that.
//                   // Log.d("SearchedResultsAni",it.first.value.toString())
//                }
//
//
//                else -> {}
//            }
//
//            when(it.second.value) {
//
//                is NetworkResult.Loading -> {
//
//                }
//
//                is NetworkResult.Success -> {
//                  //  manga = (it.first.value as NetworkResult.Success<MangaSearchResponse>).data.data
//                 //   Log.d("SearchedResultsManga",manga.toString())
//                }
//
//                is NetworkResult.Failure -> {
//                    //var error = (it.first.value as NetworkResult.Failure<MangaSearchResponse>).errorMessage
//                    Log.d("SearchedResultsManga",it.first.value.toString())
//                    Toast.makeText(requireContext(), "Due to API limitations I am not able to display both anime and manga. Sorry", Toast.LENGTH_SHORT).show()
//                }
//
//
//                else -> {}
//            }
//
//            if (anime != null && manga != null) {
//                var combined = anime.zip(manga)
//                setupUI(combined)
//            }
//
//
//        })

 //   }

//    private fun <T> setupUI(list: List<T>) {
//
//    }

}