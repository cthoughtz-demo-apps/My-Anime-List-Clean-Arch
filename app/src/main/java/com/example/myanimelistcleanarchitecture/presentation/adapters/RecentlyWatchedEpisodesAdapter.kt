package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myanimelistcleanarchitecture.databinding.DiscoveryItemListBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.watched.WatchedEpisodesResponse

class RecentlyWatchedEpisodesAdapter(private val recentlyWatchedEpisodesList: List<WatchedEpisodesResponse.Data>): Adapter<RecentlyWatchedEpisodesAdapter.RecentlyWatchedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyWatchedViewHolder {
       val binding = DiscoveryItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentlyWatchedViewHolder(binding)
    }

    override fun getItemCount() = recentlyWatchedEpisodesList.size

    override fun onBindViewHolder(holder: RecentlyWatchedViewHolder, position: Int) {

        with(holder) {
            with(recentlyWatchedEpisodesList[position]) {

                holder.binding.apply {
                    description.text = recentlyWatchedEpisodesList[position].entry.title
                }

                // Glide
                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
                Glide.with(holder.itemView.context)
                    .load(recentlyWatchedEpisodesList[position].entry.images.jpg.largeImageUrl)
                    .apply(requestOptions)
                    .into(holder.binding.animeImage)

            }
        }
    }

   inner class RecentlyWatchedViewHolder(val binding: DiscoveryItemListBinding): RecyclerView.ViewHolder(binding.root)
}