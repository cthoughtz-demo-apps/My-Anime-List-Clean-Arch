package com.example.myanimelistcleanarchitecture.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentDiscoveryBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse
import com.example.myanimelistcleanarchitecture.presentation.activities.MainActivity
import com.example.myanimelistcleanarchitecture.presentation.adapters.PopularEpisodesAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.PopularPromosEpisodeAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.RecentlyWatchedEpisodesAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.viewpagerAdapter.AllViewPagerAdapter
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.all.SearchedAllFragment
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.all.SearchedAnimeFragment
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.all.SearchedMangaFragment
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SearchViewModel
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.WatchedViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DiscoveryFragment : Fragment() {

    private var _binding: FragmentDiscoveryBinding? = null
    private val binding get() = _binding!!
    private val watchedViewModel: WatchedViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val TAG = DiscoveryFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setupVisibility(View.GONE,View.VISIBLE)
        addObservers()
        setupUI()
        searchQuery()
        setupViewPager()

    }

    private fun setupViewPager() {
        val fragments =
            listOf(SearchedAllFragment(), SearchedAnimeFragment(), SearchedMangaFragment())
        val viewPagerAdapter = AllViewPagerAdapter(
            fragments,
            (activity as MainActivity).supportFragmentManager,
            lifecycle
        )

        binding.apply {
            allViewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, allViewPager) { tab, position ->
                when (position) {

                    0 -> {
                        tab.text = getString(R.string.all)
                    }

                    1 -> {
                        tab.text = getString(R.string.anime)
                    }

                    2 -> {
                        tab.text = getString(R.string.manga)
                    }
                }
            }.attach()
        }
    }


    private fun addObservers() {
        watchedViewModel.recentlyWatchedEpisodesResponse.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Loading: $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "Success: ${it.data.data}")
                    updateRecentlyWatchEpRecyclerView(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG, "Failure: $it")
                }
            }
        })

        watchedViewModel.popularEpisodesResponse.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Loading $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "Success Popular Episodes ${it.data.data}")
                    updatePopularEpisodeRecyclerView(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG, "Failure $it")
                }
            }
        })

        watchedViewModel.recentPromos.observe(viewLifecycleOwner, Observer {

            when (it) {

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Loading $it")
                }

                is NetworkResult.Success -> {
                    Log.d(TAG, "Success Recent Promos ${it.data.data}")
                    updateRecentPromos(it.data.data)
                }

                is NetworkResult.Failure -> {
                    Log.d(TAG, "Failure $it")
                }
            }
        })
    }

    private fun updatePopularEpisodeRecyclerView(data: List<WatchedEpisodesResponse.Data>) {
        binding.apply {
            popularEpisodesRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = PopularEpisodesAdapter(data)
            }
        }
    }

    private fun updateRecentlyWatchEpRecyclerView(data: List<WatchedEpisodesResponse.Data>) {
        binding.apply {
            recentWatchedEpRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = RecentlyWatchedEpisodesAdapter(data)
            }
        }
    }

    private fun updateRecentPromos(data: List<WatchedEpisodesResponse.Data>) {
        binding.apply {
            recentPromosRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = PopularPromosEpisodeAdapter(data)
            }
        }
    }

    private fun setupUI() {
        watchedViewModel.recentlyWatchedEpisode()
        watchedViewModel.popularEpisodes()
        watchedViewModel.recentPromos()
    }

    private fun searchQuery() {
        binding.discoverySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                searchViewModel.searchAnime(query ?: "")
                searchViewModel.searchManga(query ?: "")

                binding.apply {

                    tabLayout.visibility = View.VISIBLE
                    allViewPager.visibility = View.VISIBLE

                    recentWatchedEpisodes.visibility = View.GONE
                    recentWatchedEpRecyclerView.visibility = View.GONE
                    popularEpisodes.visibility = View.GONE
                    recentPromos.visibility = View.GONE

                    /**
                     * There is a glitch if I set the all of the recycler views to
                     * view.gone it will not show the viewpager so I have to set the bottom 2 to
                     * invisible instead.
                     * */
                    popularEpisodesRecyclerView.visibility = View.INVISIBLE
                    recentPromosRecyclerView.visibility = View.INVISIBLE


                }
                //todo --> need to go to a different fragment instead of setting visibility --> Scrollview is messing with teh visibility
               // searchViewModel.searchAll(query ?: "")
                //setupVisibility(View.GONE,View.VISIBLE)
               // findNavController().navigate(R.id.detailsFragment)

              //  Toast.makeText(requireContext(), "Query: $query", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO("Not yet implemented")
                return false
            }

        })

        binding.discoverySearch.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
               // setupVisibility(View.VISIBLE,View.GONE)

                return false
            }

        })

    }

    private fun setupVisibility(firstLayoutVisibility: Int, secondLayoutVisibility: Int) {
        binding.apply {

            tabLayout.visibility = secondLayoutVisibility
            allViewPager.visibility = secondLayoutVisibility

            recentWatchedEpisodes.visibility = firstLayoutVisibility
            recentWatchedEpRecyclerView.visibility = firstLayoutVisibility
            popularEpisodes.visibility = firstLayoutVisibility
            popularEpisodesRecyclerView.visibility = firstLayoutVisibility
            recentPromos.visibility = firstLayoutVisibility
            recentPromosRecyclerView.visibility = firstLayoutVisibility


        }
    }
}