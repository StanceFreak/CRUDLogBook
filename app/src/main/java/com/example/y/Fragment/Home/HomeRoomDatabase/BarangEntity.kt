package com.example.y.Fragment.Home.HomeRoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang_table")
data class BarangEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val supplier: String,
    val quantity: String,
    val date: String
)

