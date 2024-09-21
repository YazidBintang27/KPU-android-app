package com.latihan.kpuapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.latihan.kpuapp.database.PemilihEntity
import com.latihan.kpuapp.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDataViewModel @Inject constructor(
   private val repository: RepositoryImpl
): ViewModel() {

   private var _allData = MutableStateFlow(emptyList<PemilihEntity>())
   val allData: StateFlow<List<PemilihEntity>> = _allData.asStateFlow()

   init {
      getAllData()
   }

   private fun getAllData() {
      viewModelScope.launch(Dispatchers.IO) {
         repository.getAllData().collectLatest {
            _allData.tryEmit(it)
         }
      }
   }
}