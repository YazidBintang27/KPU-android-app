package com.latihan.kpuapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.latihan.kpuapp.database.PemilihDao
import com.latihan.kpuapp.database.PemilihEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
   private val dao: PemilihDao
): Repository {

   @WorkerThread
   override suspend fun insertData(pemilihEntity: PemilihEntity) {
      dao.insertPemilih(pemilihEntity)
   }

   @WorkerThread
   override suspend fun updateData(pemilihEntity: PemilihEntity) {
      dao.updatePemilih(pemilihEntity)
   }

   @WorkerThread
   override suspend fun deleteData(pemilihEntity: PemilihEntity) {
      dao.deletePemilih(pemilihEntity)
   }

   @WorkerThread
   override suspend fun getAllData(): Flow<List<PemilihEntity>> {
      return dao.getAllPemilih()
   }

   @WorkerThread
   override suspend fun getDataByNik(nik: String): Flow<PemilihEntity> {
      return dao.getPemilihByNik(nik)
   }
}