package com.example.y.Fragment.Home.HomeRoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BarangDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(barang_table: BarangEntity)

    @Query("SELECT * from barang_table")
    fun getDataBarang() : LiveData<List<BarangEntity>>

    @Delete
    suspend fun deleteDataBarang(barang_table : BarangEntity)

}