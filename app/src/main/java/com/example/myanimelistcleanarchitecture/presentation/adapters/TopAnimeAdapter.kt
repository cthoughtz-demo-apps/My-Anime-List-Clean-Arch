package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.databinding.TopListItemBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topanime.TopAnimeResponse
import com.example.myanimelistcleanarchitecture.presentation.interfaces.RecyclerViewClickListener

class TopAnimeAdapter(
    private val topAnimeList: List<TopAnimeResponse.Data>,
    private val recyclerViewClickListener: RecyclerViewClickListener
): Adapter<TopAnimeAdapter.TopAnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAnimeViewHolder {
        val binding = TopListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopAnimeViewHolder(binding)

    }

    override fun getItemCount() = topAnimeList.size

    override fun onBindViewHolder(holder: TopAnimeViewHolder, position: Int) {

        with(holder) {
            with(topAnimeList[position]) {

                holder.binding.apply {
                    title.text =  topAnimeList[position].title
                    description.text =  holder.itemView.context.getString(R.string.anime_description,topAnimeList[position].rank)
                    holder.itemView.setOnClickListener {
                        recyclerViewClickListener.onItemClickListener(topAnimeList[position],holder.itemView.context.getString(R.string.home_fragment_anime))
                    }
                }

                // Glide
                var requestOptions = RequestOptions()
                requestOptions= requestOptions.transforms(FitCenter(), RoundedCorners(16))
                Glide.with(holder.itemView.context)
                    .load(topAnimeList[position].images.jpg.imageUrl)
                    .apply(requestOptions)
                    .into(holder.binding.picture)
            }
        }
    }

    inner class TopAnimeViewHolder(val binding: TopListItemBinding) : RecyclerView.ViewHolder(binding.root)

}