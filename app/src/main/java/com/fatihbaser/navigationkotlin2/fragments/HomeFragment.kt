package com.fatihbaser.navigationkotlin2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.adapters.PopularPagerAdapter
import com.fatihbaser.navigationkotlin2.adapters.UpcomingPagerAdapter
import com.fatihbaser.navigationkotlin2.util.DepthPageTransformer
import com.fatihbaser.navigationkotlin2.viewmodels.BaseVMFragment
import com.fatihbaser.navigationkotlin2.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseVMFragment<HomeViewModel>() {

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getPopularMovies()
        viewModel.getUpcomingMovies()

        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { popularMovie ->
            popularMovie?.let {
                lytHome.visibility = View.VISIBLE
                pagerPopularMovie.adapter = PopularPagerAdapter(popularMovie)
                pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())
            }
        })

        viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer { upcomingMovie ->
            upcomingMovie?.let {
                lytHome.visibility = View.VISIBLE
                pagerUpcomingMovie.adapter = UpcomingPagerAdapter(upcomingMovie)
                pagerUpcomingMovie.setPageTransformer(true, DepthPageTransformer())
            }
        })

        viewModel.loadingMovies.observe(viewLifecycleOwner, Observer { loadingMovies ->
            loadingMovies?.let {
                if (it){
                    homeProgressBar.visibility = View.VISIBLE
                    lytHome.visibility = View.GONE
                }else{
                    homeProgressBar.visibility = View.GONE
                }
            }
        })

    }
}
