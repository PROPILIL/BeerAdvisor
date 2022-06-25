package com.propil.beeradvisor.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BeerDao {
    //get all beers
    @Query("SELECT * FROM `Beer Database`")
    fun getBeers() : List<BeerModel>

    //get beer by id
    @Query("SELECT * FROM `Beer Database` WHERE id = (:id)")
    fun getBeerById(id : Long): BeerModel?

    //get beer by mood
    @Query("SELECT * FROM `Beer Database` WHERE `Mood for beer` = (:mood)")
    fun getBeerByMood(mood : String): BeerModel?

    //get beer by kind
    @Query("SELECT * FROM `Beer Database` WHERE `Kind (sort)` = (:kindOfBeer)")
    fun getBeerByKind(kindOfBeer : String): BeerModel?

    //get beer by color
    @Query("SELECT * FROM `Beer Database` WHERE `Color of beer` = (:colorOfBeer)")
    fun getBeerByColor(colorOfBeer: String): BeerModel?

}