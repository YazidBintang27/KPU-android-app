package com.latihan.kpuapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PemilihEntity::class], version = 1, exportSchema = false)
abstract class KpuDatabase: RoomDatabase() {
   abstract val dao: PemilihDao
}