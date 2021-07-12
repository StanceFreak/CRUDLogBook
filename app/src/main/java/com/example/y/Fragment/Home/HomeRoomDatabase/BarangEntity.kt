package com.example.y.Fragment.Home.HomeRoomDatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "barang_table")
data class BarangEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "supplier")
    val supplier: String,

    @ColumnInfo(name = "quantity")
    val quantity: String,

    @ColumnInfo(name = "date")
    val date: String
): Parcelable

