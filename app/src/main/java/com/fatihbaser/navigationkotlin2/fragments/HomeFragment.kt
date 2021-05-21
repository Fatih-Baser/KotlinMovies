package com.fatihbaser.navigationkotlin2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fatihbaser.navigationkotlin2.R
import com.fatihbaser.navigationkotlin2.viewmodels.BaseVMFragment
import com.fatihbaser.navigationkotlin2.viewmodels.HomeViewModel




class HomeFragment : BaseVMFragment<HomeViewModel>(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java


}