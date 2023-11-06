package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.databinding.ItemGenreBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse

class GenreAdapter<T>(private val list: List<T>, val fragment: String): RecyclerView.Adapter<GenreAdapter<T>.ViewHolder>() {

    var listName = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var context = holder.itemView.context

        with(holder) {
            when(fragment) {
                context.getString(R.string.home_fragment_anime) -> {
                    list as List<TopAnimeResponse.Data.Genre>
                    with(list[position]) {
                        binding.genre.text = list[position].name
                    }
                }
                context.getString(R.string.home_fragment_manga) -> {
                    list as List<TopMangaResponse.Data.Genre>
                    with(list[position]) {
                        binding.genre.text = list[position].name
                    }
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root)
}