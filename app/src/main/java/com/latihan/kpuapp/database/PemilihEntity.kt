package com.latihan.kpuapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.latihan.kpuapp.util.JenisKelamin

@Entity(tableName = "pemilih")
data class PemilihEntity(
   @PrimaryKey
   @ColumnInfo(name = "nik")
   var nik: String = "",

   @ColumnInfo(name = "nama_lengkap")
   var namaLengkap: String = "",

   @ColumnInfo(name = "nomor_handphone")
   var nomorHandphone: String = "",

   @ColumnInfo(name = "jenis_kelamin")
   var jenisKelamin: JenisKelamin = JenisKelamin.LAKILAKI,

   @ColumnInfo(name = "tanggal_pendataan")
   var tanggalPendataan: String = "",

   @ColumnInfo(name = "lokasi_pendataan")
   var lokasiPendataan: String = "",

   @ColumnInfo(name = "gambar_pendataan")
   var gambarPendataan: String = ""
)
