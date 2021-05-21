package com.fatihbaser.navigationkotlin2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.viewpager.widget.PagerAdapter
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.databinding.ItemUpcomingViewPagerBinding
import com.fatihbaser.navigationkotlin2.models.home.MovieResult


class UpcomingPagerAdapter(var upcomingMovieList: List<MovieResult>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = DataBindingUtil.inflate<ItemUpcomingViewPagerBinding>(
            inflater,
            R.layout.item_upcoming_view_pager,
            container,
            false
        )
        binding.upcoming = upcomingMovieList[position]//binding.imageView.setImageResource(images[position])
        binding.root.setOnClickListener {
            val bundle = bundleOf("movie_details" to upcomingMovieList[position])
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
        }
        container.addView(binding.root)

        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = upcomingMovieList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}