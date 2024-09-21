package com.latihan.kpuapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.kpuapp.database.PemilihEntity
import com.latihan.kpuapp.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormEntryViewModel @Inject constructor(
   private val repository: RepositoryImpl
): ViewModel() {
   fun insertData(pemilihEntity: PemilihEntity) {
      viewModelScope.launch(Dispatchers.IO) {
         repository.insertData(pemilihEntity)
      }
   }
}