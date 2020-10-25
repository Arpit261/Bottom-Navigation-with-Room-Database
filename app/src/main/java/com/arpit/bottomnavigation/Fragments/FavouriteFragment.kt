package com.arpit.bottomnavigation.Fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.arpit.bottomnavigation.Adaptor.FavouriteAdaptor
import com.arpit.bottomnavigation.Database.ListDatabase
import com.arpit.bottomnavigation.Database.ListEntity
import com.arpit.bottomnavigation.R


class FavouriteFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    lateinit var favouriteAdaptor:FavouriteAdaptor

    var dbList = listOf<ListEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_favourite, container, false)

        recyclerView=view.findViewById(R.id.recyclerView)
        layoutManager = GridLayoutManager(activity,1)

        dbList = RetriveDatabase(activity as Context).execute().get()

        if (dbList!=null && activity!=null) {
            favouriteAdaptor = FavouriteAdaptor(activity as Context, dbList)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = favouriteAdaptor


        }



        return view
    }


      class RetriveDatabase(val context: Context):  AsyncTask<Void ,Void ,List<ListEntity>>(){

          val db = Room.databaseBuilder(context ,ListDatabase::class.java,"db").build()

          override fun doInBackground(vararg p0: Void?): List<ListEntity> {
              return db.ListDao().GetAllList()
          }

      }

}