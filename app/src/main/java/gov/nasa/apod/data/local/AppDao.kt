package gov.nasa.apod.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gov.nasa.apod.data.model.ResPotd

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(resPotd: ResPotd): Long

    @Query("SELECT * FROM ResPotd WHERE date = :currentDate")
    fun getPotd(currentDate: String): ResPotd

    @Query("SELECT * FROM ResPotd")
    fun getLastPotd(): ResPotd

    @Query("DELETE FROM ResPotd")
    suspend fun delete()
}