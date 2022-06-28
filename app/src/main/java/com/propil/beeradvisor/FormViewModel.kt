package com.propil.beeradvisor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.propil.beeradvisor.database.BeerModel
import com.propil.beeradvisor.database.BeerRepository

class FormViewModel: ViewModel() {

    private val beerRepository = BeerRepository.get()
    private val beerIdLiveData = MutableLiveData<Long>()


    fun saveBeer(beerModel: BeerModel) {
        beerRepository.updateBeer(beerModel)
    }

    fun addNewBeer(beerModel: BeerModel) {
        beerRepository.insertBeer(beerModel)
    }

}