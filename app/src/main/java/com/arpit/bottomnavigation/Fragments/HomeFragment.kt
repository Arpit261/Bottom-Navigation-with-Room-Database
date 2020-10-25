package com.arpit.bottomnavigation.Fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arpit.bottomnavigation.Adaptor.HomeAdaptor
import com.arpit.bottomnavigation.R
import com.arpit.bottomnavigation.model.ListCollectation
import com.arpit.bottomnavigation.model.list
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {



    lateinit var listAdaptor:HomeAdaptor
    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView =view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(activity)
        listAdaptor=HomeAdaptor(activity as Context)
        recyclerView.adapter=listAdaptor
        recyclerView.layoutManager = layoutManager

        storeData()
        return view


    }


    fun storeData(){
        val data = ListCollectation.dataSource()
        listAdaptor.sumbitdata(data)
    }

}

