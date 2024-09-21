package com.latihan.kpuapp.viewmodel

import android.util.Log
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
class DataInformationViewModel @Inject constructor(
   private val repository: RepositoryImpl
): ViewModel() {

   private var _dataByNik = MutableStateFlow(PemilihEntity())
   val dataByNik: StateFlow<PemilihEntity> = _dataByNik.asStateFlow()

   fun getDataByNik(nik: String) {
      viewModelScope.launch(Dispatchers.IO) {
         repository.getDataByNik(nik).collectLatest {
            _dataByNik.tryEmit(it)
            Log.d("test_data_nik", it.nik)
         }
      }
   }
}