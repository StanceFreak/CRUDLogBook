package com.example.y.Fragment.Home.HomeRoomDatabase

import androidx.lifecycle.LiveData

class BarangRespository(private val barangDao: BarangDao) {

    val getDataBarang: LiveData<List<BarangEntity>> = barangDao.getDataBarang()
    suspend fun insertData(barang_table : BarangEntity) {
        barangDao.insertData(barang_table)
    }
    suspend fun deleteDataBarang(barang_table: BarangEntity) {
        barangDao.deleteDataBarang(barang_table)
    }
}