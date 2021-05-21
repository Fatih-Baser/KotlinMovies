package com.fatihbaser.navigationkotlin2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.databinding.ItemGenreBinding
import com.fatihbaser.navigationkotlin2.models.detail.MovieGenre

class GenreAdapter(var genreList: List<MovieGenre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
     class GenreViewHolder(val binding: ItemGenreBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemGenreBinding>(inflater,
            R.layout.item_genre, parent, false)
        return GenreViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.genre = genreList[position]
    }

}