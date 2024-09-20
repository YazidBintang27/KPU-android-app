package com.latihan.kpuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.latihan.kpuapp.R
import com.latihan.kpuapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

   private lateinit var binding: FragmentSplashBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      // Inflate the layout for this fragment
      return FragmentSplashBinding.inflate(inflater, container, false).root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
   }
}