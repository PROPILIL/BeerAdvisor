package com.propil.beeradvisor.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = com.propil.beeradvisor.utils.DATABASE_NAME)
data class BeerModel(@PrimaryKey(autoGenerate = true) val id: Long = 0,

                     @ColumnInfo(name = "Beer name")
                     var beerName: String = "",

                     @ColumnInfo(name = "Description")
                     var beerDescription: String = "",

                     @ColumnInfo(name = "Color of beer") //TODO: You have to convert the Set to String
                     val beerTags: Set<String> = emptySet())

