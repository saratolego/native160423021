package com.saradev.satukelompok_anmp_projectuts.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saradev.satukelompok_anmp_projectuts.model.Measurement
import com.ubaya.anmp_week3.util.FileHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) :
    AndroidViewModel(application) {
        private val fileHelper = FileHelper(getApplication())
    val measurements = MutableLiveData<List<Measurement>>()

    fun loadMeasurementData() {
        viewModelScope.launch(Dispatchers.IO) {
        val rawData = fileHelper.readFromFile()

        val dataList = mutableListOf<Measurement>()
        val lines = rawData.split('\n')

        lines.forEach { line ->
            if (line.isNotBlank()) {
                val parts = line.split(",")
                if (parts.size == 3) {
                    dataList.add(Measurement(parts[0], parts[1], parts[2]))
                }
            }
        }
        measurements.postValue(dataList)}
    }
    fun addMeasurementData(age: String, height: String, weight: String) {
        if (age.isBlank() || height.isBlank() || weight.isBlank()) {
            return
        }
        val dataLine = "$age,$height,$weight"
        fileHelper.writeToFile(dataLine)
        Log.d("print_file", fileHelper.getFilePath())   
    }

    private val PREFS_NAME = "profil_anak_prefs"
    private val KEY_NAMA = "key_nama"
    private val KEY_TGL_LAHIR = "key_tgl_lahir"
    private val KEY_JENIS_KELAMIN_ID = "key_jenis_kelamin_id"

    private val sharedPrefs: SharedPreferences

    val namaProfil = MutableLiveData<String>()
    val tglLahirProfil = MutableLiveData<String>()
    val jenisKelaminId = MutableLiveData<Int>()

    init {
        sharedPrefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadProfileData()
    }
    fun saveProfileData(nama: String, tglLahir: String, idJenisKelamin: Int) {
        val editor = sharedPrefs.edit()
        editor.putString(KEY_NAMA, nama)
        editor.putString(KEY_TGL_LAHIR, tglLahir)
        editor.putInt(KEY_JENIS_KELAMIN_ID, idJenisKelamin)
        editor.apply()

        namaProfil.value = nama
        tglLahirProfil.value = tglLahir
        jenisKelaminId.value = idJenisKelamin
    }

    private fun loadProfileData() {
        namaProfil.value = sharedPrefs.getString(KEY_NAMA, "") ?: ""
        tglLahirProfil.value = sharedPrefs.getString(KEY_TGL_LAHIR, "") ?: ""
        jenisKelaminId.value = sharedPrefs.getInt(KEY_JENIS_KELAMIN_ID, -1)
    }
    }

