package com.zygotecnologia.zygotv.database

import androidx.room.*
import com.zygotecnologia.zygotv.modelGenre.Genre

@Dao
interface MoviesDAO {
    @Query("SELECT * FROM genre")
    fun getShows(): List<Genre>

    @Delete
    suspend fun delete(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genre: Genre)
}
