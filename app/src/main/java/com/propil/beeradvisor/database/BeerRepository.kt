package com.propil.beeradvisor.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class BeerRepository private constructor(context: Context){

    //for storing links from BeerDatabase and objects from BeerDao
    private val database: BeerDatabase =
        Room.databaseBuilder(context.applicationContext,
            BeerDatabase::class.java,
            com.propil.beeradvisor.utils.DATABASE_NAME)
            .build()

    private val beerDao = database.beerDao()
    private val executor = Executors.newSingleThreadExecutor()

    //GETTERS
    fun getBeers(): LiveData<List<BeerModel>> = beerDao.getBeers()

    fun getBeerById(id: Long): LiveData<BeerModel?> = beerDao.getBeerById(id)

    fun updateBeer(beerModel: BeerModel) {
        executor.execute {
            beerDao.updateBeer(beerModel)
        }
    }

    fun insertBeer(beerModel: BeerModel) {
        executor.execute {
            beerDao.insertBeer(beerModel)
        }
    }
//
//    fun getBeerByMood(mood: String): BeerModel? = beerDao.getBeerByMood(mood)
//
//    fun getBeerByKind(kind: String): BeerModel? = beerDao.getBeerByKind(kind)
//
//    fun getBeerByColor(color: String): BeerModel? = beerDao.getBeerByColor(color)


    companion object {
        private var INSTANCE: BeerRepository? = null


        fun initialize(context: Context) {
            when (INSTANCE) {
                null -> INSTANCE = BeerRepository(context)
            }
        }

        fun get(): BeerRepository {
            return INSTANCE ?: throw
            IllegalStateException("BeerRepository must be initialized")
        }
    }
}