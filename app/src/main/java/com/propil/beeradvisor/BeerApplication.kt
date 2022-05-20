package com.propil.beeradvisor

import android.app.Application

class BeerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        BeerRepository.initialize(this)
    }
}