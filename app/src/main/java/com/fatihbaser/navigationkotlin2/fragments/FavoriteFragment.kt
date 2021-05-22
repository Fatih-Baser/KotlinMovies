package com.fatihbaser.navigationkotlin2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.adapters.MovieAdapter
import com.fatihbaser.navigationkotlin2.viewmodels.BaseVMFragment
import com.fatihbaser.navigationkotlin2.viewmodels.MovieDetailViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : BaseVMFragment<MovieDetailViewModel>() {

    override fun getViewModel(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerviewFavorites.layoutManager = GridLayoutManager(context!!, 2)
                recyclerviewFavorites.adapter = MovieAdapter(it) {
                    val bundle = bundleOf("movie_details" to it)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_favoriteFragment_to_movieDetailFragment, bundle)
                }
            }

        })
    }
}
