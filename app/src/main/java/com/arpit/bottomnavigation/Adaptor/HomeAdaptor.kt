package com.arpit.bottomnavigation.Adaptor

import android.app.AlertDialog
import android.app.Application
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.arpit.bottomnavigation.Database.ListDatabase
import com.arpit.bottomnavigation.Database.ListEntity
import com.arpit.bottomnavigation.R
import com.arpit.bottomnavigation.model.ListCollectation
import com.arpit.bottomnavigation.model.list
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_description.view.*
import kotlinx.android.synthetic.main.each_row_home.view.*

class HomeAdaptor(val context:Context) :RecyclerView.Adapter<HomeAdaptor.ListViewHolder>() {
    private var itemList: List<list> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.each_row_home, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                holder.bind(itemList[position])

                holder.getAllFav(context,itemList[position])

            }




        }

        val dialog =Dialog(context)
        dialog.setContentView(R.layout.dialog_description)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        holder.relativeContent.setOnClickListener {

            val text_name = dialog.findViewById<TextView>(R.id.TxtName)
            val image_dialog =dialog.findViewById<ImageView>(R.id.ImageDialog)
            val text_description =dialog.findViewById<TextView>(R.id.TxtDescription)

           val button = dialog.findViewById<Button>(R.id.btnOk)

            text_name.setText(itemList.get(holder.adapterPosition).Name)

            text_description.setText(itemList.get(holder.adapterPosition).Description)

           Picasso.get().load(itemList.get(position).PlacePath).into(image_dialog)
            dialog.show()
            dialog.setCancelable(false)

            button.setOnClickListener {
                dialog.dismiss()
            }

        }


    }



        /*
        val listOfFavourite = GetAllFav(context).execute().get()
        if (listOfFavourite.isNotEmpty() && listOfFavourite.contains(text.id.toString())) {
            holder.favImage.setImageResource(R.drawable.favourite_filled)
        } else {
            holder.favImage.setImageResource(R.drawable.favourite_unfilled)
        }

       holder.favImage.setOnClickListener {
            val listEntity = ListEntity(
                text.id,
                text.listName,
                text.listPrice

            )
            if (!DbAsyncTask(context, listEntity, 1).execute().get()) {
                val async = DbAsyncTask(context, listEntity, 2).execute()
                val result = async.get()

                if (result) {
                    Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show()
                    holder.favImage.setImageResource(R.drawable.favourite_filled)
                }
            }
            else {
                val async = DbAsyncTask(context, listEntity, 3).execute()
                val result = async.get()
                if (result) {
                    Toast.makeText(context, "Remove from favourite", Toast.LENGTH_SHORT).show()
                    holder.favImage.setImageResource(R.drawable.favourite_unfilled)
                }

            }

        }
         holder.relativContent.setOnClickListener {
            val dialog = AlertDialog.Builder(context)

        }

         */





    fun sumbitdata(list: ArrayList<list>) {
        itemList = list
    }


    class ListViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        val listName = view.NameList
        val listPrice = view.priceList
        val listImage = view.ImageView
        val favImage = view.imgFav
        val relativeContent = view.relativeContent


        fun bind(list: list) {
            listName.text = list.Name
            listPrice.text = list.Place

            Picasso.get().load(list.PlacePath).error(R.drawable.ic_launcher_foreground)
                .into(listImage)
        }
         fun getAllFav(context: Context ,list: list){
             val listOfFav = GetAllFav(context).execute().get()

             if (listOfFav.isNotEmpty() && listOfFav.contains(itemId.toString())) {
                 favImage.setImageResource(R.drawable.favourite_filled)
             } else {
                 favImage.setImageResource(R.drawable.favourite_unfilled)
             }

                 favImage.setOnClickListener {
                 val listEntity = ListEntity(
                     list.id,
                     list.Name,
                     list.Place



                 )
                 if (!DbAsyncTask(context, listEntity, 1).execute().get()) {
                     val async = DbAsyncTask(context, listEntity, 2).execute()
                     val result = async.get()

                     if (result) {
                         Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show()
                         favImage.setImageResource(R.drawable.favourite_filled)
                     }
                 }
                 else {
                     val async = DbAsyncTask(context, listEntity, 3).execute()
                     val result = async.get()
                     if (result) {
                         Toast.makeText(context, "Remove from favourite", Toast.LENGTH_SHORT).show()
                         favImage.setImageResource(R.drawable.favourite_unfilled)
                     }

                 }

             }


         }

        }






          class DbAsyncTask(context: Context, val listEntitity: ListEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, ListDatabase::class.java, "db").build()

        override fun doInBackground(vararg p0: Void?): Boolean {

            when (mode) {
                1 -> {
                    val list: ListEntity? = db.ListDao().GetListById(listEntitity.id.toString())
                    db.close()
                    return list != null
                }

                2 -> {
                    db.ListDao().insert(listEntitity)
                    db.close()
                    return true
                }

                3 -> {
                    db.ListDao().delete(listEntitity)
                    db.close()
                    return true
                }
            }
            return false
        }

    }

    class GetAllFav(context: Context) : AsyncTask<Void, Void, List<String>>() {
        val db = Room.databaseBuilder(context, ListDatabase::class.java, "db").build()
        override fun doInBackground(vararg p0: Void?): List<String> {

            val list = db.ListDao().GetAllList()
            val ListOfIDS = arrayListOf<String>()

            for (i in list) {
                ListOfIDS.add(i.id.toString())
            }
            return ListOfIDS
        }

    }

    }





