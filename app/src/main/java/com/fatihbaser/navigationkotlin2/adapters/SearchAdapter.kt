package com.fatihbaser.navigationkotlin2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.databinding.ItemSearchBinding
import com.fatihbaser.navigationkotlin2.models.search.MovieSearchResult


class SearchAdapter(
    val searchList: List<MovieSearchResult>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchBinding>(
            inflater,
            R.layout.item_search,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.search = searchList[position]
    }
}