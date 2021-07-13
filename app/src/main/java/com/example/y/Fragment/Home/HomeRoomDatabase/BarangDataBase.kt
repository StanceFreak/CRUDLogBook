package com.example.y.Fragment.Home.HomeRoomDatabase

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
        entities = [BarangEntity::class],
        version = 2,
        exportSchema = false,
)
abstract class BarangDataBase : RoomDatabase() {
    abstract fun barangDao() : BarangDao

    companion object{
        @Volatile
        private var INSTANCE: BarangDataBase? = null

        fun getDatabase(context: Context): BarangDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BarangDataBase::class.java,
                    "barang_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}