package com.propil.beeradvisor.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BeerModel::class], version = 1, exportSchema = false)
//@TypeConverters(SomeConverter::class) - you can't use complicated types of data in DB, need converter
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}