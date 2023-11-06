package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myanimelistcleanarchitecture.databinding.SeasonalItemListBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse

class LastSeasonAdapter(private val lastSeasonList: List<SeasonalResponse.Data>): Adapter<LastSeasonAdapter.LastSeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSeasonViewHolder {
        val binding =
            SeasonalItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LastSeasonViewHolder(binding)
    }

    override fun getItemCount(): Int = lastSeasonList.size

    override fun onBindViewHolder(holder: LastSeasonViewHolder, position: Int) {

        with(holder) {
            with(lastSeasonList[position]) {
                holder.binding.apply {
                    score.text = lastSeasonList[position].score.toString()
                    scoreBy.text = lastSeasonList[position].scoredBy.toString()
                    title.text = lastSeasonList[position].title

                    var updatedGenre = ""
                    for (i in lastSeasonList[position].genres) {
                        updatedGenre += "${i.name}"
                    }
                    category.text = updatedGenre
                }
            }

            // Glide
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
            Glide.with(holder.itemView.context)
                .load(lastSeasonList[position].images.jpg.imageUrl)
                .apply(requestOptions)
                .into(holder.binding.seasonalImage)
        }
    }

    class LastSeasonViewHolder(val binding: SeasonalItemListBinding): RecyclerView.ViewHolder(binding.root)
}