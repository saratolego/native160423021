package com.saradev.satukelompok_anmp_projectuts.view.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.ubaya.anmp_week3.util.FileHelper

class ListViewModel(application: Application) :
    AndroidViewModel(application) {
        private val fileHelper = FileHelper(getApplication())
    fun addMeasurementData(age: String, height: String, weight: String) {
        if (age.isBlank() || height.isBlank() || weight.isBlank()) {
            return
        }
        val dataLine = "$age,$height,$weight"
        fileHelper.writeToFile(dataLine)
}
    }