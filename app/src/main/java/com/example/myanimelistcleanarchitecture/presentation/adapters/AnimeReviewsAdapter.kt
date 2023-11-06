package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myanimelistcleanarchitecture.R
import com.example.myanimelistcleanarchitecture.databinding.ItemReviewBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.reviews.AnimeReviewsResponse
import com.example.myanimelistcleanarchitecture.presentation.utils.CommonUtils

class AnimeReviewsAdapter(val list: List<AnimeReviewsResponse.Data>) : Adapter<AnimeReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(list[position]) {
                binding.apply {
                        reviewerRating.text = holder.itemView.context.getString(
                            R.string.score_rating,
                            list[position].score.toString()
                        )
                        reviewUserName.text = list[position].user.username
                        date.text = CommonUtils.convertDate(list[position].date)
                        Log.d("CheckDate", "Date: ${list[position].date}")
                        review.text = list[position].review
                }
            }
        }
    }

    inner class ViewHolder(val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root)
}