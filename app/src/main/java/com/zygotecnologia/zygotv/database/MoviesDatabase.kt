package com.zygotecnologia.zygotv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.modelGenre.Genre

@Database(entities = [Genre::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun genresDAO(): MoviesDAO
}
