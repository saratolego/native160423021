package com.saradev.satukelompok_anmp_projectuts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class Profile(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val nama: String,
    val tglLahir: String,
    val jenisKelamin: Int
)