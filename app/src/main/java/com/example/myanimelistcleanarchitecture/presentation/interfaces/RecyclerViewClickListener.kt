package com.example.myanimelistcleanarchitecture.presentation.interfaces

interface RecyclerViewClickListener {

    fun <T> onItemClickListener(data: T, type:String)
}