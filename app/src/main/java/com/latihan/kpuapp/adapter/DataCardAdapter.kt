package com.latihan.kpuapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.latihan.kpuapp.database.PemilihEntity
import com.latihan.kpuapp.databinding.DataCardBinding

class DataCardAdapter: RecyclerView.Adapter<DataCardAdapter.ViewHolder>() {

   private var listData: List<PemilihEntity> = listOf()
   private lateinit var onItemClickCallback: OnItemClickCallback

   interface OnItemClickCallback {
      fun onItemClicked(nik: String)
   }

   fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
      this.onItemClickCallback = onItemClickCallback
   }

   class ViewHolder(val binding: DataCardBinding): RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = DataCardBinding.inflate(layoutInflater, parent, false)
      return ViewHolder(binding)
   }

   override fun onBindViewHolder(holder: DataCardAdapter.ViewHolder, position: Int) {
      val (nik, nama, nohp) = listData[position]
      holder.binding.tvNomor.text = (position + 1).toString()
      holder.binding.tvNikData.text = nik
      holder.binding.tvNamaData.text = nama
      holder.binding.tvNohpData.text = nohp
      holder.itemView.setOnClickListener {
         Log.d("DataCardAdapter", "NIK yang diklik: $nik")
         onItemClickCallback.onItemClicked(nik)
      }
   }

   override fun getItemCount(): Int {
      return listData.size
   }

   @SuppressLint("NotifyDataSetChanged")
   fun setData(listData: List<PemilihEntity>) {
      this.listData = listData
      notifyDataSetChanged()
   }
}