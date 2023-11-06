package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.databinding.ItemSearchedBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.AnimeSearchResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.search.MangaSearchResponse

class SearchedAdapter<T>(val list: List<T>, val fragment: String): RecyclerView.Adapter<SearchedAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemSearchedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var context = holder.itemView.context
        Log.d("CheckingFragment","fragment: --> $fragment")

        with(holder) {

            when(fragment) {
                context.getString(R.string.searched_anime_fragment) -> {
                    list as List<AnimeSearchResponse.Data>
                    with(list[position]) {
                        binding.apply {
                            title.text = list[position].title
                            ranking.text = list[position].rank.toString()
                            type.text = list[position].type
                            type.setBackgroundColor(context.getColor(R.color.red))
                            members.text = list[position].members.toString()
                            epOrVol.text = context.getString(R.string.episodes,list[position].episodes.toString()) //list[position].episodes.toString()
                            additionalinfo.text = list[position].duration

                            Glide.with(context)
                                .load(list[position].images.jpg.imageUrl)
                                .into(holder.binding.profile)
                        }
                    }
                }
                context.getString(R.string.searched_all_fragment) -> {
                    /**
                     * There is an issue with the endpoint not able to display all at once. When making request to anime
                     * and then manga due to request limitations one comes back empty which makes in pointless to display
                     * anything.
                     * */
                }
                context.getString(R.string.searched_manga_fragment) -> {
                    list as List<MangaSearchResponse.Data>
                    with(list[position]) {
                        binding.apply {
                            title.text = list[position].title
                            ranking.text = list[position].rank.toString()
                            type.text = list[position].title
                            type.setBackgroundColor(context.getColor(R.color.orange))
                            members.text = list[position].members.toString()
                            epOrVol.text = context.getString(R.string.volumes,list[position].volumes,list[position].chapters)

                            Glide.with(context)
                                .load(list[position].images.jpg.imageUrl)
                                .into(holder.binding.profile)
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemSearchedBinding): RecyclerView.ViewHolder(binding.root)

}