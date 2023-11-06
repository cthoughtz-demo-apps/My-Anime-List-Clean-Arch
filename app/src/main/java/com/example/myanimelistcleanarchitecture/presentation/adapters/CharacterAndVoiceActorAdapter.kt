package com.example.myanimelistcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.myanimelistcleanarchitecture.databinding.ItemCharacterVoiceActorProfilesBinding
import com.example.myanimelistcleanarchitecture.domain.model.Response.staff.CharactersAndVoiceActorsResponse

class CharacterAndVoiceActorAdapter( val list: List<CharactersAndVoiceActorsResponse.Data>): Adapter<CharacterAndVoiceActorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterVoiceActorProfilesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.apply {
                    characterName.text = list[position].character.name
                    if (list[position].voiceActors.isNotEmpty()) {
                        voiceActorName.text = list[position].voiceActors[0].person.name
                    }
                }
            }
        }

        // Glide
        Glide.with(holder.itemView.context)
            .load(list[position].character.images.jpg.imageUrl)
            .into((holder.binding.characterImage))

        if (list[position].voiceActors.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(list[position].voiceActors[0].person.images.jpg.imageUrl)
                .into(holder.binding.voiceActorImage)
        }
    }

    inner class ViewHolder(val binding: ItemCharacterVoiceActorProfilesBinding): RecyclerView.ViewHolder(binding.root)
}