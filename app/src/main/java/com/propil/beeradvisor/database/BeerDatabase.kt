package com.propil.beeradvisor.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BeerModel::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class BeerDatabase : RoomDatabase() {

    //TODO: Man in video added here an instance

    abstract fun beerDao(): BeerDao
}