package com.latihan.kpuapp.fragments

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.kpuapp.R
import com.latihan.kpuapp.adapter.DataCardAdapter
import com.latihan.kpuapp.databinding.FragmentListDataBinding
import com.latihan.kpuapp.viewmodel.ListDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListDataFragment : Fragment() {

   private lateinit var binding: FragmentListDataBinding
   private val dataCardAdapter: DataCardAdapter = DataCardAdapter()
   private val listDataViewModel: ListDataViewModel by viewModels()
   private lateinit var navController: NavController

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      binding = FragmentListDataBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      navController = Navigation.findNavController(view)
      FragmentListDataBinding.bind(view)
      binding.myToolbar.setNavigationOnClickListener {
         navController.navigateUp()
      }
      getAllData()
      setupAdapter()
   }

   private fun setupAdapter() {
      binding.rvData.apply {
         adapter = dataCardAdapter
         setHasFixedSize(true)
         layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
      }
      dataCardAdapter.setOnItemClickCallback(object: DataCardAdapter.OnItemClickCallback {
         override fun onItemClicked(nik: String) {
            val toDataInformationFragment = ListDataFragmentDirections.actionListDataFragmentToDataInformationFragment()
            toDataInformationFragment.nik = nik
            Log.d("data_fragment", "NIK diterima: $nik")
            navController.navigate(toDataInformationFragment)
         }
      })
   }

   private fun getAllData() {
      lifecycleScope.launch {
         listDataViewModel.allData.collectLatest {
            dataCardAdapter.setData(it)
         }
      }
   }
}