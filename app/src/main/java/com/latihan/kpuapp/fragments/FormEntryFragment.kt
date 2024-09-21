package com.latihan.kpuapp.fragments

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.latihan.kpuapp.R
import com.latihan.kpuapp.database.PemilihEntity
import com.latihan.kpuapp.databinding.FragmentFormEntryBinding
import com.latihan.kpuapp.util.JenisKelamin
import com.latihan.kpuapp.viewmodel.FormEntryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class FormEntryFragment : Fragment(), View.OnClickListener {

   private lateinit var binding: FragmentFormEntryBinding
   private lateinit var navController: NavController
   private lateinit var fusedLocationClient: FusedLocationProviderClient
   private val formEntryViewModel: FormEntryViewModel by viewModels()

   private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
      if (uri != null) {
         binding.tvImage.text = uri.toString()
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      binding = FragmentFormEntryBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      FragmentFormEntryBinding.bind(view)
      binding.myToolbar.setNavigationOnClickListener {
         navController.navigateUp()
      }
      fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
      binding.etAlamat.setOnClickListener(this)
      binding.etTanggal.setOnClickListener(this)
      binding.btnImagePicker.setOnClickListener(this)
      binding.btnSubmit.setOnClickListener(this)
   }

   private fun insertData() {
      if (binding.etNik.text.toString().isNotEmpty()) {
         val nik: String = binding.etNik.text.toString()
         val nama: String = binding.etNama.text.toString()
         val noHp: String = binding.etNohp.text.toString()
         val jenisKelamin: JenisKelamin = if (binding.radioL.isChecked) {
            JenisKelamin.LAKILAKI
         } else {
            JenisKelamin.PEREMPUAN
         }
         val tanggalPendataan: String = binding.etTanggal.text.toString()
         val lokasiPendataan: String = binding.etAlamat.text.toString()
         val gambarPendataan: String = binding.tvImage.text.toString()
         val pemilihEntity: PemilihEntity = PemilihEntity(nik, nama, noHp, jenisKelamin,
            tanggalPendataan, lokasiPendataan, gambarPendataan)
         lifecycleScope.launch {
            formEntryViewModel.insertData(pemilihEntity)
         }
         Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
         navController.navigateUp()
      } else {
         Toast.makeText(context, "Masukkan NIK", Toast.LENGTH_SHORT).show()
      }
   }

   private fun setDatePicker(editText: EditText) {
      val calendar: Calendar = Calendar.getInstance()
      val nowYear = calendar.get(Calendar.YEAR)
      val nowMonth = calendar.get(Calendar.MONTH)
      val nowDay = calendar.get(Calendar.DAY_OF_MONTH)

      val datePickerDialog = DatePickerDialog(requireContext(),
         { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            val dateFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            editText.setText(dateFormat.format(cal.time))
         }, nowYear, nowMonth, nowDay)
      datePickerDialog.show()
   }

   private fun getCurrentLocation() {
      if (ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
         ) != PackageManager.PERMISSION_GRANTED
      ) {
         ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            101
         )
         return
      }
      fusedLocationClient.lastLocation.addOnSuccessListener { location ->
         if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            try {
               val addresses = geocoder.getFromLocation(latitude, longitude, 1)
               if (!addresses.isNullOrEmpty()) {
                  val address = addresses[0].getAddressLine(0)
                  binding.etAlamat.setText(address)
               }
            } catch (e: IOException) {
               e.printStackTrace()
            }
         } else {
            Log.e("Location", "Lokasi tidak ditemukan")
         }
      }
   }

   private fun openGallery() {
      if (ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
         ) != PackageManager.PERMISSION_GRANTED
      ) {
         ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            102
         )
      } else {
         imagePickerLauncher.launch("image/*")
      }
   }

   @Deprecated("Deprecated in Java")
   override fun onRequestPermissionsResult(
      requestCode: Int,
      permissions: Array<out String>,
      grantResults: IntArray
   ) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults)
      if (requestCode == 102) {
         if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
         } else {
            Toast.makeText(requireContext(), "Izin dibutuhkan untuk mengakses galeri", Toast.LENGTH_SHORT).show()
         }
      } else if (requestCode == 101) {
         if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
         } else {
            Toast.makeText(requireContext(), "Izin lokasi dibutuhkan", Toast.LENGTH_SHORT).show()
         }
      }
   }

   override fun onClick(v: View?) {
      when (v?.id) {
         R.id.et_tanggal -> setDatePicker(binding.etTanggal)
         R.id.et_alamat -> getCurrentLocation()
         R.id.btn_image_picker -> openGallery()
         R.id.btn_submit -> insertData()
      }
   }
}