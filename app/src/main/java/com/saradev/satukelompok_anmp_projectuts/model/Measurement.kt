package com.saradev.satukelompok_anmp_projectuts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "measurement_table")
data class Measurement(
    val age: String,
    val height: String,
    val weight: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
//data class Measurement(
//    val age: String,
//    val height: String,
//    val weight: String
//)