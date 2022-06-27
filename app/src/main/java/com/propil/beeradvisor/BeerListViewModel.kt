package com.propil.beeradvisor

import androidx.lifecycle.ViewModel
import com.propil.beeradvisor.database.BeerRepository

class BeerListViewModel: ViewModel() {

    private val beerRepository =  BeerRepository.get()
    val beerListLiveData = beerRepository.getBeers()
}