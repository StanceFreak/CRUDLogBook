package com.example.y.Fragment.Home.HomeRoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BarangViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BarangRespository
    val getDataBarang : LiveData<List<BarangEntity>>

    init {
        val barangDao = BarangDataBase.getDatabase(application).barangDao()
        repository = BarangRespository(barangDao)
        getDataBarang = repository.getDataBarang
    }
     fun insertData(barang_table : BarangEntity) {
        viewModelScope.launch {
            repository.insertData(barang_table)
        }
    }

    fun updateBarang(barang_table: BarangEntity) {
        viewModelScope.launch {
            repository.updateBarang(barang_table)
        }
    }

    fun deleteDataBarang(BarangId: Int) {
        viewModelScope.launch {
            repository.deleteDataBarang(BarangId)
        }
    }

}