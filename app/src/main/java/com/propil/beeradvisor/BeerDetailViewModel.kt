package com.propil.beeradvisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.propil.beeradvisor.database.BeerModel
import com.propil.beeradvisor.database.BeerRepository

class BeerDetailViewModel: ViewModel(){

    private val beerRepository = BeerRepository.get()
    private val beerIdLiveData = MutableLiveData<Long>()

    var beerLiveData: LiveData<BeerModel?> = Transformations.switchMap(beerIdLiveData) {
        beerId -> beerRepository.getBeerById(beerId)
    }

    fun loadBeer(beerId: Long) {
        beerIdLiveData.value = beerId
    }
}