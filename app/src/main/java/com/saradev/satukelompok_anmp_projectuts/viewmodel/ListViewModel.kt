package com.saradev.satukelompok_anmp_projectuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saradev.satukelompok_anmp_projectuts.model.Measurement
import com.saradev.satukelompok_anmp_projectuts.model.MeasurementDatabase
import com.saradev.satukelompok_anmp_projectuts.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val measurements = MutableLiveData<List<Measurement>>()

    val namaProfil = MutableLiveData<String>("")
    val tglLahirProfil = MutableLiveData<String>("")
    val jenisKelaminId = MutableLiveData<Int>(-1)

    val inputAge = MutableLiveData<String>("")
    val inputHeight = MutableLiveData<String>("")
    val inputWeight = MutableLiveData<String>("")

    private fun getDb(): MeasurementDatabase {
        return MeasurementDatabase.invoke(getApplication())
    }

    fun loadMeasurementData() {
        viewModelScope.launch(Dispatchers.IO) {
            val db = getDb()
            val dataList = db.measurementDao().selectAllMeasurements()
            measurements.postValue(dataList)
        }
    }

    fun addMeasurementData() {
        val age = inputAge.value ?: ""
        val height = inputHeight.value ?: ""
        val weight = inputWeight.value ?: ""

        if (age.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                val newMeasurement = Measurement(age, height, weight)
                getDb().measurementDao().insertAll(newMeasurement)

                inputAge.postValue("")
                inputHeight.postValue("")
                inputWeight.postValue("")

                loadMeasurementData()
            }
        }
    }

    fun saveProfileData() {
        val nama = namaProfil.value ?: ""
        val tgl = tglLahirProfil.value ?: ""
        val jk = jenisKelaminId.value ?: -1

        viewModelScope.launch(Dispatchers.IO) {
            val profile = Profile(id = 1, nama = nama, tglLahir = tgl, jenisKelamin = jk)
            getDb().profileDao().insertOrUpdate(profile)
        }
    }

    fun loadProfileData() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = getDb().profileDao().getProfile()
            if (profile != null) {
                namaProfil.postValue(profile.nama)
                tglLahirProfil.postValue(profile.tglLahir)
                jenisKelaminId.postValue(profile.jenisKelamin)
            }
        }
    }

    init {
        loadProfileData()
        loadMeasurementData()
    }
}
