package com.propil.beeradvisor

import androidx.lifecycle.ViewModel

class BeerViewModel: ViewModel() {

    private val beerRepository =  BeerRepository.get()
    val beers = beerRepository.getBeers()
}