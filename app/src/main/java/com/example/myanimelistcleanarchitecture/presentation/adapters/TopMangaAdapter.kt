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
import com.example.myanimelistcleanarchitecture.domain.model.Response.top.topmanga.TopMangaResponse
import com.example.myanimelistcleanarchitecture.presentation.interfaces.RecyclerViewClickListener

class TopMangaAdapter(
    private val topMangaList: List<TopMangaResponse.Data>,
    private val recyclerViewClickListener: RecyclerViewClickListener
): Adapter<TopMangaAdapter.TopMangaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMangaViewHolder {
        val binding = TopListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopMangaViewHolder(binding)
    }

    override fun getItemCount() = topMangaList.size

    override fun onBindViewHolder(holder: TopMangaViewHolder, position: Int) {

        with(holder) {
            with(topMangaList[position]) {

                holder.binding.apply {
                    title.text = topMangaList[position].title
                    description.text = holder.itemView.context.getString(R.string.manga_description, topMangaList[position].rank)

                    holder.itemView.setOnClickListener {
                        recyclerViewClickListener.onItemClickListener(topMangaList[position],holder.itemView.context.getString(R.string.home_fragment_manga))
                    }
                }

                // Glide
                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
                Glide.with(holder.itemView.context)
                    .load(topMangaList[position].images.jpg.imageUrl)
                    .apply(requestOptions)
                    .into(holder.binding.picture)
            }
        }
    }

    inner class TopMangaViewHolder(val binding: TopListItemBinding): RecyclerView.ViewHolder(binding.root)
}