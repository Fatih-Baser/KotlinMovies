package com.fatihbaser.navigationkotlin2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.databinding.ItemTrailerBinding
import com.fatihbaser.navigationkotlin2.models.videos.MovieVideoResult


class TrailerAdapter(var trailerList: List<MovieVideoResult>, val trailerOnClick: (MovieVideoResult)->Unit) : RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {
    class TrailerViewHolder(val binding: ItemTrailerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(trailer: MovieVideoResult, trailerOnClick: (MovieVideoResult) -> Unit){
            itemView.setOnClickListener { trailerOnClick(trailer) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTrailerBinding>(inflater,
            R.layout.item_trailer, parent, false)
        return TrailerViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.binding.trailer = trailerList[position]
        holder.bind(trailerList[position],trailerOnClick)
    }
}