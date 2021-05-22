package com.fatihbaser.navigationkotlin2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.adapters.SearchAdapter
import com.fatihbaser.navigationkotlin2.databinding.FragmentSearchBinding
import com.fatihbaser.navigationkotlin2.util.gone
import com.fatihbaser.navigationkotlin2.util.visible
import com.fatihbaser.navigationkotlin2.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun getViewModel(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dataBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.length > 0){
                    viewModel.getSearchMovies(query!!)
                    dataBinding.imgSearch.gone()
                    dataBinding.tvSearch.gone()
                    dataBinding.recyclerviewSearch.visible()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        dataBinding.searchView.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                dataBinding.recyclerviewSearch.gone()
                dataBinding.imgSearch.visible()
                dataBinding.tvSearch.visible()
                return false
            }
        })

        viewModel.searchMovies.observe(viewLifecycleOwner, Observer {searchMovie ->
            searchMovie?.let {
                recyclerviewSearch.layoutManager = LinearLayoutManager(context!!)
                recyclerviewSearch.adapter = SearchAdapter(searchMovie)
            }

        })
    }


}