package com.example.myanimelistcleanarchitecture.presentation.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentHomeBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.TopAnimeAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.TopMangaAdapter
import com.example.myanimelistcleanarchitecture.presentation.interfaces.RecyclerViewClickListener
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.TopViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val topViewModel: TopViewModel by activityViewModels()
    private val TAG =  HomeFragment::class.java.simpleName
    lateinit var recyclerViewClickListener: RecyclerViewClickListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addObservers()
        otherClickListeners()
        setupUI()
    }

    private fun otherClickListeners() {

        recyclerViewClickListener = object :  RecyclerViewClickListener {

            override fun <T> onItemClickListener(data: T, type: String) {

                // Todo --> Change Manga Response Data Class to parcel to stop failing
                activity?.intent?.putExtra("data",data as Parcelable)
                var bundle = bundleOf()

                if (type == getString(R.string.home_fragment_anime)) {
                    bundle = bundleOf("fragmentName" to getString(R.string.home_fragment_anime))
                } else if (type == getString(R.string.home_fragment_manga)) {
                    bundle = bundleOf("fragmentName" to getString(R.string.home_fragment_manga))
                }
               /**
                * I am having issues passing the bundle. I am getting a NullPointer Exception which
                * is why I am using activity.intent to pass the data instead of passing the bundle
                * into the navigate function.
                * */
                findNavController().navigate(R.id.detailsFragment,bundle)
            }

        }
    }

    private fun addObservers() {

        topViewModel.topAnimeResponse.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Loading: $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "Success: $it")
                    Log.d(TAG, "Success: ${it.data}")
                    updateAnimeRecyclerView(it.data.topAnimeData)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG, "Failure: $it")

                }
            }
        })


        // Manga
        topViewModel.topMangaResponse.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {
                    Log.d("TestingManga", "Loading: $it")
                }

                is NetworkResult.Success -> {
                    Log.d("TestingManga", "Success: ${it.data.data}")
                    updateMangaRecyclerView(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d("TestingManga", "Failure: ${it}")
                }
            }
        })
    }

    private fun updateAnimeRecyclerView(data: List<TopAnimeResponse.Data>) {
            binding.apply {
                animeRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = TopAnimeAdapter(data, recyclerViewClickListener)
                }
            }
    }

    private fun updateMangaRecyclerView(data: List<TopMangaResponse.Data>) {
        binding.apply {
            mangaRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = TopMangaAdapter(data, recyclerViewClickListener)
            }
        }
    }

    private fun setupUI() {
        topViewModel.topAnime()
        topViewModel.topManga()
    }
}
