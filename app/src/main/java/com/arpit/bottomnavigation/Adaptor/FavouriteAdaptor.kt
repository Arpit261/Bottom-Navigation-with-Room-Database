package com.arpit.bottomnavigation.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.bottomnavigation.Database.ListEntity
import com.arpit.bottomnavigation.R

class FavouriteAdaptor(val context :Context ,val itemList:List<ListEntity>) :RecyclerView.Adapter<FavouriteAdaptor.FavouriteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
          val view = LayoutInflater.from(parent.context).inflate(R.layout.each_row_favourite ,parent ,false)
        return FavouriteViewHolder(view)
    }

    override fun getItemCount(): Int {
      return itemList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
       val text = itemList[position]
        holder.listName.text=text.Listname
        holder.listPrice.text=text.ListPlace

    }




    class FavouriteViewHolder(view: View):RecyclerView.ViewHolder(view){
        val listName: TextView = view.findViewById(R.id.NameList)
        val listPrice: TextView = view.findViewById(R.id.priceList)


    }


}