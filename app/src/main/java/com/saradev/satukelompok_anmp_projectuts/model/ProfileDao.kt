package com.saradev.satukelompok_anmp_projectuts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(profile: Profile)

    @Query("SELECT * FROM profile_table WHERE id = 1")
    fun getProfile(): Profile?
}