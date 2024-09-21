package com.latihan.kpuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.latihan.kpuapp.R
import com.latihan.kpuapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), View.OnClickListener {

   private lateinit var binding: FragmentDashboardBinding
   private lateinit var navController: NavController

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      binding = FragmentDashboardBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      FragmentDashboardBinding.bind(view)
      binding.informasi.setOnClickListener(this)
      binding.formEntry.setOnClickListener(this)
      binding.lihatData.setOnClickListener(this)
      binding.keluar.setOnClickListener(this)
   }

   override fun onClick(v: View?) {
      when (v?.id) {
         R.id.informasi -> navController.navigate(R.id.action_dashboardFragment_to_informationFragment)
         R.id.form_entry -> navController.navigate(R.id.action_dashboardFragment_to_formEntryFragment)
         R.id.lihat_data -> navController.navigate(R.id.action_dashboardFragment_to_listDataFragment)
         R.id.keluar -> activity?.finishAffinity()
      }
   }
}