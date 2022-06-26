package com.propil.beeradvisor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.beer_list_fragment_container)

        if (currentFragment == null){
            val fragment = BeerListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.beer_list_fragment_container, fragment)
                .commit()
        }
    }


}