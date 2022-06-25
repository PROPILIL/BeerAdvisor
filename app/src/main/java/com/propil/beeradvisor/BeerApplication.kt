package com.propil.beeradvisor

import android.app.Application
import com.propil.beeradvisor.database.BeerRepository

class BeerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        BeerRepository.initialize(this)
    }
}