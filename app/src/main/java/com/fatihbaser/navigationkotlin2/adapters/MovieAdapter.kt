package com.fatihbaser.navigationkotlin2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.databinding.ItemMovieBinding
import com.fatihbaser.navigationkotlin2.models.home.MovieResult


class MovieAdapter(val movieDetailList: List<MovieResult>, val onItemClick:(MovieResult)->Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(model: MovieResult, onItemClick: (MovieResult) -> Unit){
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieDetailList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movieDetailList[position]
        holder.bind(movieDetailList[position],onItemClick)
    }
}