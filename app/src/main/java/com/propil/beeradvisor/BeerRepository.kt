package com.propil.beeradvisor

import android.content.Context
import androidx.room.Room
import com.propil.beeradvisor.database.BeerDatabase
import java.lang.IllegalStateException

class BeerRepository private constructor(context: Context){

    //for storing links from BeerDatabase and objects from BeerDao
    private val database: BeerDatabase =
        Room.databaseBuilder(context.applicationContext, BeerDatabase::class.java, com.propil.beeradvisor.utils.DATABASE_NAME).build()

    private val beerDao = database.beerDao()

    //GETTERS
    fun getBeers(): List<BeerModel> = beerDao.getBeers()

    fun getBeerById(id: Long): BeerModel? = beerDao.getBeerById(id)

    fun getBeerByMood(mood: String): BeerModel? = beerDao.getBeerByMood(mood)

    fun getBeerByKind(kind: String): BeerModel? = beerDao.getBeerByKind(kind)

    fun getBeerByColor(color: String): BeerModel? = beerDao.getBeerByColor(color)


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