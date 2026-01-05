package com.saradev.satukelompok_anmp_projectuts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MeasurementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg measurements: Measurement)

    @Query("SELECT * FROM measurement_table ORDER BY id DESC")
    fun selectAllMeasurements(): List<Measurement>
}
