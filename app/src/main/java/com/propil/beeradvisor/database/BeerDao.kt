package com.propil.beeradvisor.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BeerDao {

    //INSERTS
    @Insert(onConflict = OnConflictStrategy.REPLACE) //TODO: Check docs about OnConflictStrategy
    fun insertBeer(beerModel: BeerModel) // TODO: Now I don't know how it works, but this fun for inserting one beer, I guess

    //UPDATES
    @Update
    fun updateBeer(beerModel: BeerModel) // TODO: Now I don't know how it works, but this fun for updating one beer, I guess

    //QUERIES
    @Query("SELECT * FROM `Beer Database`") // To get all beers
    fun getBeers() : LiveData<List<BeerModel>> //LiveData observing added

    @Query("SELECT * FROM `Beer Database` WHERE id = (:id)") // To get one exactly beer by its id number
    fun getBeerById(id : Long): LiveData<BeerModel?> //LiveData observing added

    //DELETES
    @Delete
    fun deleteBeer(beerModel: BeerModel) //To delete one beer by <I don't know by what>
}




//@Query("SELECT * FROM `Beer Database` WHERE `Mood for beer` = (:mood)")
//    fun getBeerByMood(mood : String): LiveData<BeerModel?>
//
//    @Query("SELECT * FROM `Beer Database` WHERE `Kind (sort)` = (:kindOfBeer)")
//    fun getBeerByKind(kindOfBeer : String): LiveData<BeerModel?>
//
//    @Query("SELECT * FROM `Beer Database` WHERE `Color of beer` = (:colorOfBeer)")
//    fun getBeerByColor(colorOfBeer: String): LiveData<BeerModel?>