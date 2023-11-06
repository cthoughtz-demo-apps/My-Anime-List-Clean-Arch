package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.myanimelistcleanarchitecture.databinding.ItemStaffProfileBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.AnimeStaffResponse

class AnimeStaffAdapter(val list: List<AnimeStaffResponse.Data>): Adapter<AnimeStaffAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStaffProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                holder.binding.apply {
                    staffName.text  = list[position].person.name
                    producerName.text = list[position].positions[0]
                }
            }

            // Glide
            Glide.with(holder.itemView.context)
                .load(list[position].person.images.jpg.imageUrl)
                .into(holder.binding.staffImage)
        }
    }

    class ViewHolder(val binding: ItemStaffProfileBinding): RecyclerView.ViewHolder(binding.root)

}