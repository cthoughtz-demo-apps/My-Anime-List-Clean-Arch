package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myanimelistcleanarchitecture.databinding.ItemThemeBinding


class ThemesAdapterOpenings(val list: List<String>): Adapter<ThemesAdapterOpenings.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(list[position]) {
                // Todo --> Change color of first word later
                binding.themeText.text = list[position]
            }
        }
    }

    inner class ViewHolder(val binding: ItemThemeBinding): RecyclerView.ViewHolder(binding.root)

}