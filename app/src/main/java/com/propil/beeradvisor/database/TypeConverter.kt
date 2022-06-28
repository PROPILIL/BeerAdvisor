package com.propil.beeradvisor.database

import androidx.room.TypeConverter

class TypeConverter {

    @TypeConverter
    fun fromMapToString(map: Map<String, String>?): String {
        return map?.entries?.joinToString(separator = "\n") ?: "Nothing to convert"
    }

    @TypeConverter
    fun fromStringToMap(string: String?): Map<String, String>{
        return string?.split("\n")?.associate {
            val (key, value) = it.split("=")
            key to value
        } ?: return emptyMap()
    }
}