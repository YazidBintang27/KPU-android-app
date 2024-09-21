package com.latihan.kpuapp.repository

import com.latihan.kpuapp.database.PemilihEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
   suspend fun insertData(pemilihEntity: PemilihEntity)

   suspend fun updateData(pemilihEntity: PemilihEntity)

   suspend fun deleteData(pemilihEntity: PemilihEntity)

   suspend fun getAllData(): Flow<List<PemilihEntity>>

   suspend fun getDataByNik(nik: String): Flow<PemilihEntity>
}