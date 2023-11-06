package com.example.myanimelistcleanarchitecture.presentation.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import com.bumptech.glide.Glide
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.data.NetworkResult
import com.example.myanimelistcleanarchitecture.databinding.FragmentDetailsBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.MangaReviewsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.AnimeStaffResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.CharactersAndVoiceActorsResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.presentation.adapters.AnimeReviewsAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.AnimeStaffAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.CharacterAndVoiceActorAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.GenreAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.MangaReviewsAdapter
import com.example.myanimelistcleanarchitecture.presentation.adapters.ThemesAdapterEndings
import com.example.myanimelistcleanarchitecture.presentation.adapters.ThemesAdapterOpenings
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.ReviewViewModel
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.ThemeViewModel
import com.example.myanimelistcleanarchitecture.presentation.viewmodels.StaffViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var args: Any? = null
    var bundle = ""

    private val staffViewModel: StaffViewModel by activityViewModels()
    private val themeViewModel: ThemeViewModel by activityViewModels()
    private val reviewViewModel: ReviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundle = arguments?.getString("fragmentName") ?: "No Fragment"

        when(bundle) {
            getString(R.string.home_fragment_anime) -> {
               args = activity?.intent?.getParcelableExtra<TopAnimeResponse.Data>("data") // TODO --> Add Data to cosntant file
                setupUI(args, getString(R.string.home_fragment_anime))
            }
            getString(R.string.home_fragment_manga) -> {
                args = activity?.intent?.getParcelableExtra<TopMangaResponse.Data>("data")
                setupUI(args, getString(R.string.home_fragment_manga))
            }
           else -> "Nothing"
        }

        addObserversTopAnime()
        addObserversTopManga()
    }

    private fun addObserversTopManga() {
        reviewViewModel.mangaReviews.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {}

                is NetworkResult.Success -> {
                    reviewRecyclerView(it.data.data,getString(R.string.home_fragment_manga))
                }

                is NetworkResult.Failure -> {

                }
            }
        })
    }

    private  fun addObserversTopAnime() {
        staffViewModel.staff.observe(viewLifecycleOwner, Observer {
            when(it) {

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                   // updateCharactersAndVoiceActorRecyclerView(it.data.data)
                    if (bundle == getString(R.string.home_fragment_anime)) {
                        updateStaff(it.data.data)
                    }
                }

                is NetworkResult.Failure -> {

                }
            }
        })

        staffViewModel.charactersVoiceActors.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    if (bundle == getString(R.string.home_fragment_anime)) {
                        updateCharactersAndVoiceActorRecyclerView(it.data.data)
                    }
                }

                is NetworkResult.Failure -> {
                    Log.d("CharcterVoiceActorFailure",it.errorMessage)
                }
            }
        })

        themeViewModel.theme.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    if (bundle == getString(R.string.home_fragment_anime)) {
                        updateThemeRecyclerView(it.data.data.openings, it.data.data.endings)
                        Log.d("CheckingThemeResponse", it.data.data.toString())
                    }
                }

                is NetworkResult.Failure -> {
                    Log.d("CheckingThemeResponse",it.errorMessage)
                    if (it.errorMessage == getString(R.string.too_many_request)) {
                        Toast.makeText(
                            requireContext(),
                            "Open/Close Theme has to many request at this time please wait an try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })

        reviewViewModel.animeReviews.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    if (bundle == getString(R.string.home_fragment_anime)) {
                        reviewRecyclerView(it.data.data,getString(R.string.home_fragment_anime))
                    }
                }

                is NetworkResult.Failure -> {
                    Log.d("CheckErrorMessages","Error: ${it.errorMessage}")
                    if (it.errorMessage == getString(R.string.too_many_request)) {
                        Toast.makeText(
                            requireContext(),
                            "Reviews has to many request at this time please wait an try again",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    private fun <T> reviewRecyclerView(data: List<T>, fragment: String) {
        binding.apply {
            reviewsRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)

                if (fragment == getString(R.string.home_fragment_anime)) {

                    adapter = AnimeReviewsAdapter(data as List<AnimeReviewsResponse.Data>)
                } else if(fragment == getString(R.string.home_fragment_manga)) {
                    adapter = MangaReviewsAdapter(data as List<MangaReviewsResponse.Data>)
                }
            }
        }
    }

    private fun updateThemeRecyclerView(openings: List<String>, endings: List<String>) {

        binding.apply {
            openingRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

                adapter = ThemesAdapterOpenings(openings)
            }

            endingThemeRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = ThemesAdapterEndings(endings)
            }
        }
    }

    private fun updateStaff(data: List<AnimeStaffResponse.Data>) {
        binding.apply {
            // todo --> Will not display the recyclerview because it make the UI look to ugly
            staffRecycleView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = AnimeStaffAdapter(data)
            }
        }
    }

    private fun updateCharactersAndVoiceActorRecyclerView(data: List<CharactersAndVoiceActorsResponse.Data>) {
        binding.apply {
            charactersAndVoiceActor.apply {
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                adapter = CharacterAndVoiceActorAdapter(data)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun <T>setupUI(args: T?, fragment: String) {

         when(fragment) {
             getString(R.string.home_fragment_anime) -> {
                args as TopAnimeResponse.Data
                setupUITopAnime(args)
            }
             getString(R.string.home_fragment_manga) -> {
                 args as TopMangaResponse.Data
                setupUITopManga(args)
             }
             else -> ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupUITopManga(args: TopMangaResponse.Data) {

        reviewViewModel.getReviewsManga(args.malId)

        binding.apply {
            detailScore.text = args.score.toString()
            ranked.text = args.rank.toString()
            popularity.text = args.popularity.toString()
            members.text = args.members.toString()
            favorites.text = args.favorites.toString()
            title.text = args.title
            type.text = args.type
            aired.text = args.status
            episode.text = getString(R.string.volumes, args.volumes,args.chapters)
            genre.apply {
                layoutManager  =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                adapter = GenreAdapter(args.genres, getString(R.string.home_fragment_manga))
            }

            synopsis.text = args.synopsis
            published.text = CommonUtils.convertDate(args.published.from)
            serialization.text = getSerialization(args.serializations,getString(R.string.home_fragment_manga))
            authors.text = getAuthors(args.authors,getString(R.string.home_fragment_manga))

            arrowClickListeners()

            Glide.with(requireContext())
                .load(args.images.jpg.largeImageUrl)
                .into(detailedFragmentImage)
        }

        topAnimeSetVisibility(View.GONE)
        topMangaSetVisibility(View.VISIBLE)

    }

    private fun setupUITopAnime(args: TopAnimeResponse.Data) {

        staffViewModel.getStaff(args.malId)
        staffViewModel.getCharactersAndVoiceActors(args.malId)
        reviewViewModel.getReviewsAnime(args.malId)
        themeViewModel.getThemes(args.malId)

        binding.apply {
            detailScore.text = args.score.toString()
            ranked.text = args.rank.toString()
            popularity.text = args.popularity.toString()
            members.text = args.members.toString()
            favorites.text = args.favorites.toString()
            title.text = args.title
            type.text = args.type
            year.text = args.year.toString()
            aired.text = args.status
            episode.text= getString(R.string.episodes,args.episodes.toString())
            duration.text = args.duration.dropLast(6)
            genre.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = GenreAdapter(args.genres,getString(R.string.home_fragment_anime))
            }
            synopsis.text = args.synopsis

            arrowClickListeners()
            setupYoutubeVideo(args.trailer.youtubeId)

            englishTitle.text = args.titleEnglish
            source.text = args.source
            studio.text = args.studios.get(0).name
            rating.text = args.rating
            season.text = args.season

            licensor.text = getLicensor(args.licensors, getString(R.string.home_fragment_anime))//licenses.drop(1)

            Glide.with(requireContext())
                .load(args.images.jpg.largeImageUrl)
                .into(detailedFragmentImage)
        }

        topAnimeSetVisibility(View.VISIBLE)
        topMangaSetVisibility(View.GONE)

    }

    private fun topAnimeSetVisibility(visibility: Int) {
        binding.apply {
            sourceLabel.visibility = visibility
            youtubeVideoWebView.visibility = visibility
            englishLabel.visibility = visibility
            studioLabel.visibility = visibility
            ratingLabel.visibility = visibility
            seasonLabel.visibility = visibility
            airedLabel.visibility = visibility
            licensorLabel.visibility = visibility
            openingThemeLabel.visibility = visibility
            openingRecyclerView.visibility = visibility
            endingThemeLabel.visibility = visibility
            endingThemeRecyclerView.visibility = visibility
            charactersAndVoiceActor.visibility = visibility
        }
    }

    private fun topMangaSetVisibility(visibility: Int) {
        binding.apply {
            publishedLabel.visibility = visibility
            serializationLabel.visibility = visibility
            authorsLabel.visibility = visibility
        }
    }


    private fun <T> getLicensor(licensorsList: List<T>, fragment: String): String {

        var liscenses = ""

         if (fragment == getString(R.string.home_fragment_anime)) {
             var updatedType = licensorsList as List<TopAnimeResponse.Data.Licensor>

             for (i in updatedType) {
                 liscenses += ", ${i.name}"
             }
             return liscenses.drop(1)
         }

        return getString(R.string.something_went_wrong)
    }

    private fun <T> getSerialization(serialization: List<T>, fragment: String): String {

        var serializes = ""

        if (fragment == getString(R.string.home_fragment_manga)) {
            var updateSerialization = serialization as List<TopMangaResponse.Data.Serialization>

            for (i in updateSerialization) {
                serializes += ", ${i.name}"
            }
            return serializes.drop(1)
        }
        return "Something Went Wrong."
    }

    private fun <T> getAuthors(authorsList: List<T>, fragment: String): String {

        var authors = ""

        if (fragment == getString(R.string.home_fragment_manga)) {
            var updateAuthors = authorsList as List<TopMangaResponse.Data.Author>

            for (i in updateAuthors) {
                authors += ", ${i.name}"
            }
            return authors.drop(1)
        }
        return "Something Went Wrong."
    }

    private fun setupYoutubeVideo(youtubeId: String) {
        binding.apply {
            var video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$youtubeId\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
            youtubeVideoWebView.apply {
                loadData(video, "text/html", "utf-8")
                settings.javaScriptEnabled = true
                webChromeClient = WebChromeClient()
                visibility = View.VISIBLE
            }
        }
    }


    private fun arrowClickListeners() {
        // TODO --> MAKE ERROR ANIMATE
        binding.apply {
            downArrow.setOnClickListener {
                it.visibility = View.GONE
                upArrow.visibility = View.VISIBLE

                var params = synopsis.layoutParams
                params.height = LayoutParams.WRAP_CONTENT
                synopsis.layoutParams = params
            }

            upArrow.setOnClickListener {
                it.visibility = View.GONE
                downArrow.visibility = View.VISIBLE

                var params = synopsis.layoutParams
                params.height = 750
                synopsis.layoutParams = params
            }
        }
    }
}