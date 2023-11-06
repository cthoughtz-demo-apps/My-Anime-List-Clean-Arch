package com.example.myanimelistcleanarchitecture.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.databinding.FragmentSeasonalBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse
import com.example.myanimelistcleanarchitecture.presentation.activities.MainActivity
import com.example.myanimelistcleanarchitecture.presentation.adapters.viewpagerAdapter.SeasonalViewPagerAdapter
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.seasonal.LastFragment
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.seasonal.NextFragment
import com.example.myanimelistcleanarchitecture.presentation.fragments.viewpagerfragments.seasonal.ThisSeasonFragment
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils.getCurrentSeason
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils.getCurrentYear
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils.getLastSeason
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils.getNextSeason
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.SeasonalViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


open class SeasonalFragment() : Fragment() {

    private var _binding: FragmentSeasonalBinding? = null
    private val binding get() = _binding!!
    private val seasonalViewModel: SeasonalViewModel by activityViewModels()
    private val TAG = SeasonalFragment::class.java.simpleName
    var testList: List<SeasonalResponse.Data>?= null
   var currentTab = ""
    var filter = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSeasonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
      //  addObservers()
    }

    private fun setupUI() {
        setupViewPager()
        setupSpinner()
    }

    private fun setupViewPager() {

        val fragments = listOf(LastFragment(),ThisSeasonFragment(),NextFragment())
        val viewPagerAdapter = SeasonalViewPagerAdapter(
            fragments,
            (activity as MainActivity).supportFragmentManager,
            lifecycle
        )

        binding.apply {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout,viewPager) { tab, position ->
                when (position) {

                    0 -> {
                        tab.text = getString(R.string.last_season)
                    }
                    1 -> {
                        tab.text = getString(R.string.this_season)
                    }

                    2 -> {
                        tab.text = getString(R.string.next_season)
                    }
                }
            }.attach()


            // Default
            binding.season.text =  "${getLastSeason().capitalize()} ${getCurrentYear()}"
            tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.text) {
                        getString(R.string.last_season) -> binding.season.text = "${getLastSeason().capitalize()} ${getCurrentYear()}"
                        getString(R.string.this_season) -> binding.season.text = "${getCurrentSeason().capitalize()} ${getCurrentYear()}"
                        getString(R.string.next_season) -> binding.season.text = "${getNextSeason().capitalize()} ${getCurrentYear()}"
                    }
                    currentTab = tab!!.text.toString()
                    Log.d("CurrentChecker","Current Tab Tab: $currentTab")
                    Log.d("CurrentChecker","Current Filter Tab: $filter")

                    when {
                        currentTab == getString(R.string.last_season) -> {
                            seasonalViewModel.getSeasonal(getCurrentYear(), getLastSeason(),filter)
                        }
                        currentTab == getString(R.string.next_season) -> {
                            seasonalViewModel.getSeasonal(getCurrentYear(), getNextSeason(),filter)
                        }
                        currentTab == getString(R.string.this_season) -> {
                            seasonalViewModel.getSeasonal(getCurrentYear(), getCurrentSeason(),filter)
                        }
                    }

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    //TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                   // TODO("Not yet implemented")
                }

            })
        }
    }

    private fun setupSpinner() {

        val filterItems = resources.getStringArray(R.array.filter)
        val spinnerAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line,filterItems)
        binding.spinner.dropDownVerticalOffset = 150
        binding.spinner.adapter = spinnerAdapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                filter = when (position) {
                    0 -> "tv"
                    1 -> "ona"
                    2 -> "ova"
                    3 -> "movie"
                    4 -> "music"
                    5 -> "special"
                    else -> "tv"
                }


                when {
                    currentTab == getString(R.string.last_season) -> {
                        seasonalViewModel.getSeasonal(getCurrentYear(), getLastSeason(),filter)
                    }
                    currentTab == getString(R.string.next_season) -> {
                        seasonalViewModel.getSeasonal(getCurrentYear(), getNextSeason(),filter)
                    }
                    currentTab == getString(R.string.this_season) -> {
                        seasonalViewModel.getSeasonal(getCurrentYear(), getCurrentSeason(),filter)
                    }
                    else -> {
                        seasonalViewModel.getSeasonal(getCurrentYear(), getLastSeason(),filter)
                    }
                }


                seasonalViewModel.getSeasonal(getCurrentYear(),getCurrentSeason(),filter)
                Log.d("CurrentChecker","Current Tab Spinner: $currentTab")
                Log.d("CurrentChecker","Current Filter Spinner: $filter")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // write code to preform some action
            }

        }
    }


}