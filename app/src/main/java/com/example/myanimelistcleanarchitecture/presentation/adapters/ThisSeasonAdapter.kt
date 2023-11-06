package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myanimelistcleanarchitecture.databinding.SeasonalItemListBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.seasonal.SeasonalResponse

class ThisSeasonAdapter(private val thisSeasonList: List<SeasonalResponse.Data>): Adapter<ThisSeasonAdapter.ThisSeasonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThisSeasonViewHolder {
        val binding =
            SeasonalItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThisSeasonViewHolder(binding)
    }

    override fun getItemCount() = thisSeasonList.size

    override fun onBindViewHolder(holder: ThisSeasonViewHolder, position: Int) {

        with(holder) {
            with(thisSeasonList[position]) {
                holder.binding.apply {
                    score.text = thisSeasonList[position].score.toString()
                    scoreBy.text = thisSeasonList[position].scoredBy.toString()
                    title.text = thisSeasonList[position].title


                    var updatedGenre = ""
                    for (i in thisSeasonList[position].genres) {
                        updatedGenre += "${i.name} "
                    }

                    category.text = updatedGenre
                }
            }

            // Glide
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
            Glide.with(holder.itemView.context)
                .load(thisSeasonList[position].images.jpg.imageUrl)
                .apply(requestOptions)
                .into(holder.binding.seasonalImage)
        }
    }

    class ThisSeasonViewHolder(val binding: SeasonalItemListBinding): RecyclerView.ViewHolder(binding.root)
}