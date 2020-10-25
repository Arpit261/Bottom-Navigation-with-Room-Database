package com.arpit.bottomnavigation.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arpit.bottomnavigation.Fragments.FavouriteFragment
import com.arpit.bottomnavigation.Fragments.HomeFragment
import com.arpit.bottomnavigation.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openHome()

        bottomAppBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->{
                   supportFragmentManager.beginTransaction().replace(R.id.frameLayout,
                       HomeFragment()).commit()


                }
                R.id.fav->{
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout ,FavouriteFragment()).commit()
                   
                }
            }
                 return@setOnNavigationItemSelectedListener true
        }

    }

    fun openHome(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
            .commit()
    }
}