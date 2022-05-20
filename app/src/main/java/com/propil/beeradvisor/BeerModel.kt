package com.propil.beeradvisor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BeerModel(@PrimaryKey val id: Long = 0,
                     var beerName: String = "",
                     var beerDescription: String = "",
                     val beerKind: String = "",
                     val beerMood: String = "",
                     val beerColor: String = "")

