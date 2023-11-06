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

class PopularPromosEpisodeAdapter(private val popularPromosEpisodeList: List<WatchedEpisodesResponse.Data>) :
RecyclerView.Adapter<PopularPromosEpisodeAdapter.PopularPromosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPromosViewHolder {
        val binding = DiscoveryItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularPromosViewHolder(binding)
    }

    override fun getItemCount() = popularPromosEpisodeList.size

    override fun onBindViewHolder(holder: PopularPromosViewHolder, position: Int) {

        with(holder) {
            with(popularPromosEpisodeList[position]) {

                holder.binding.apply {
                    description.text = popularPromosEpisodeList[position].entry.title
                }

                // Glide
                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transform(FitCenter(), RoundedCorners(16))
                Glide.with(holder.itemView.context)
                    .load(popularPromosEpisodeList[position].entry.images.jpg.largeImageUrl)
                    .apply(requestOptions)
                    .into(holder.binding.animeImage)

            }
        }
    }

    inner class PopularPromosViewHolder(val binding: DiscoveryItemListBinding): RecyclerView.ViewHolder(binding.root)

}