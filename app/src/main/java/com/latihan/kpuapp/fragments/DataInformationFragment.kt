package com.latihan.kpuapp.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.latihan.kpuapp.R
import com.latihan.kpuapp.database.PemilihEntity
import com.latihan.kpuapp.databinding.FragmentDashboardBinding
import com.latihan.kpuapp.databinding.FragmentDataInformationBinding
import com.latihan.kpuapp.viewmodel.DataInformationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DataInformationFragment : Fragment() {

   private lateinit var binding: FragmentDataInformationBinding
   private lateinit var navController: NavController
   private val dataInformationViewModel: DataInformationViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      binding = FragmentDataInformationBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      FragmentDataInformationBinding.bind(view)
      binding.myToolbar.setNavigationOnClickListener {
         navController.navigateUp()
      }
      getData()
   }

   private fun getData() {
      lifecycleScope.launch {
         val nik = DataInformationFragmentArgs.fromBundle(arguments as Bundle).nik
         Log.d("data_args", nik)
         dataInformationViewModel.getDataByNik(nik)
         dataInformationViewModel.dataByNik.collectLatest {
            Log.d("test_data", it.nik)
            binding.tvNikData.text = it.nik
            binding.tvNamaData.text = it.namaLengkap
            binding.tvNohpData.text = it.nomorHandphone
            binding.tvJkData.text = it.jenisKelamin.toString()
            binding.tvTanggalData.text = it.tanggalPendataan
            binding.tvAlamatData.text = it.lokasiPendataan
            binding.ivGambarData.setImageURI(Uri.parse(it.gambarPendataan))
         }
      }
   }
}