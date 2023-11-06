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

class NextSeasonAdapter(private val nextSeasonList: List<SeasonalResponse.Data>): Adapter<NextSeasonAdapter.NextSeasonViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextSeasonViewHolder {
        val binding = SeasonalItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NextSeasonViewHolder(binding)
    }

    override fun getItemCount() = nextSeasonList.size

    override fun onBindViewHolder(holder: NextSeasonViewHolder, position: Int) {

        with(holder) {
            with(nextSeasonList[position]) {
                holder.binding.apply {
                    score.text = nextSeasonList[position].score.toString()
                    scoreBy.text = nextSeasonList[position].scoredBy.toString()
                    title.text = nextSeasonList[position].title

                    var updatedGenre = ""
                    for (i in nextSeasonList[position].genres) {
                        updatedGenre += "${i.name}"
                    }
                    category.text = updatedGenre
                }
            }

            // Glide
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
            Glide.with(holder.itemView.context)
                .load(nextSeasonList[position].images.jpg.imageUrl)
                .apply(requestOptions)
                .into(holder.binding.seasonalImage)

        }
    }

    class NextSeasonViewHolder(val binding: SeasonalItemListBinding): RecyclerView.ViewHolder(binding.root)
}