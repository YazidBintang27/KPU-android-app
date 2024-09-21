package com.latihan.kpuapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PemilihDao {

   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertPemilih(pemilih: PemilihEntity)

   @Update
   suspend fun updatePemilih(pemilih: PemilihEntity)

   @Delete
   suspend fun deletePemilih(pemilih: PemilihEntity)

   @Query("SELECT * FROM pemilih")
   fun getAllPemilih(): Flow<List<PemilihEntity>>

   @Query("SELECT * FROM pemilih WHERE nik = :nik")
   fun getPemilihByNik(nik: String): Flow<PemilihEntity>
}