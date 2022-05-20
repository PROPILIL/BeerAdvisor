package com.propil.beeradvisor.database

import androidx.room.Dao
import androidx.room.Query
import com.propil.beeradvisor.BeerModel

@Dao
interface BeerDao {
    //get all beers
    @Query("SELECT * FROM beermodel")
    fun getBeers() : List<BeerModel>

    //get beer by id
    @Query("SELECT * FROM beermodel WHERE id = (:id)")
    fun getBeerById(id : Long): BeerModel?

    //get beer by mood
    @Query("SELECT * FROM beermodel WHERE beerMood = (:mood)")
    fun getBeerByMood(mood : String): BeerModel?

    //get beer by kind
    @Query("SELECT * FROM beermodel WHERE beerKind = (:kindOfBeer)")
    fun getBeerByKind(kindOfBeer : String): BeerModel?

    //get beer by color
    @Query("SELECT * FROM beermodel WHERE beerColor = (:colorOfBeer)")
    fun getBeerByColor(colorOfBeer: String): BeerModel?

}