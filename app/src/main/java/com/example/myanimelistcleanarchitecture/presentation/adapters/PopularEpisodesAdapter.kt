package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myanimelistcleanarchitecture.databinding.DiscoveryItemListBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse

class PopularEpisodesAdapter(private val popularsEpisodesList: List<WatchedEpisodesResponse.Data>) :
    RecyclerView.Adapter<PopularEpisodesAdapter.PopularEpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularEpisodesViewHolder {
        val binding = DiscoveryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularEpisodesViewHolder(binding)
    }

    override fun getItemCount() = popularsEpisodesList.size

    override fun onBindViewHolder(holder: PopularEpisodesViewHolder, position: Int) {

        with(holder) {
            with(popularsEpisodesList[position]) {

                holder.binding.apply {
                    description.text = popularsEpisodesList[position].entry.title
                }

                // Glide
                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
                Glide.with(holder.itemView.context)
                    .load(popularsEpisodesList[position].entry.images.jpg.largeImageUrl)
                    .apply(requestOptions)
                    .into(holder.binding.animeImage)
            }
        }
    }

    inner class PopularEpisodesViewHolder(val binding: DiscoveryItemListBinding): RecyclerView.ViewHolder(binding.root)

}