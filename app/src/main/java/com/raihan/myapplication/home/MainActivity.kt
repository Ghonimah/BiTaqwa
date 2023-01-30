package com.raihan.myapplication.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityMainBinding
import com.raihan.myapplication.doa.DoaActivity
import com.raihan.myapplication.home.adapter.InspirationAdapter
import com.raihan.myapplication.home.data.InspirationData
import com.raihan.myapplication.home.model.InspirationModel
import com.raihan.myapplication.jadwalsholat.JadwalSholatActivity
import com.raihan.myapplication.videokajian.VideoKajianActivity
import com.raihan.myapplication.zakat.ZakatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    // deklarasi variable koneksi komponen xml ke file kotlin
    private lateinit var binding: ActivityMainBinding

    // perintah dalam onCreate akan dijalankan ketika activity pertama dibuka
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // inisialisasi variable binding dengan layoutinflater
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initnavMenu()
        initHeader()
        iniRecyclerViewInspiration()
    }

    private fun iniRecyclerViewInspiration() {
        // buat variable penampung kumpulan data
        val list: ArrayList<InspirationModel> = arrayListOf()
        // tambahkan data ke dalam list dari InspirationData
        list.addAll(InspirationData.listData)

        // panggil adapter dan masukkan list ke dalamnya
        val  inspirationAdapter = InspirationAdapter(list)

        // pengaturan recyclerview
        binding.rvInspiration.setHasFixedSize(true)
        binding.rvInspiration.layoutManager = LinearLayoutManager(this)

        // pasang adapter ke recyclerview
        binding.rvInspiration.adapter = inspirationAdapter
    }

    private fun initHeader() {
        // mengambil waktu sekarang
        val timeNow = Calendar.getInstance()
        // menentukan format jam HH (hour 2 digit)
        val timeFormat = SimpleDateFormat("HH")
        // membentuk waktu sekarang hanya jam saja
        val time = timeFormat.format(timeNow.time)

        // tentukan gambar berdasarkan jam sekarang (data di variable time)
        when {
            // jam 00 - 06 gambarnya malam
            time.toInt() in 0..6 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_night)
            }
            // jam 07 - 12 siang gambarnya pagi
            time.toInt() in 7..12 -> {
                // ganti gambar jadi pagi
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_morning)
            }
            // jam 13 - 18 gambarnya siang
            time.toInt() in 13..18 -> {
                // ganti gambar jadi sore
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_afternoon)
            }
            // jam 19 - 23 gambarnya malam
            time.toInt() in 19..23 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_night)
            }
        }
    }

    private fun initnavMenu() {
        binding.ivIconMenuDoa.setOnClickListener {
            // memulai aktifitas baru
            // memberikan intent untuk menunjukkan tujuan pergi
            startActivity(Intent(this, DoaActivity::class.java))
        }

        binding.ivIconMenuDzikir.setOnClickListener {
        }

        binding.ivIconMenuJadwalSholat.setOnClickListener {
            startActivity(Intent(this, JadwalSholatActivity::class.java))
        }

        binding.ivIconMenuZakat.setOnClickListener {
            startActivity(Intent(this, ZakatActivity::class.java))
        }

        binding.ivIconMenuVideoKajian.setOnClickListener {
            startActivity(Intent(this,VideoKajianActivity::class.java))
        }
    }

    // perintah dalam onPause akan dijalankan ketika activity tertutup
    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause: ketutup ")
    }
    // perintah dalam onResume akan dijalankan ketika activity dibuka kembali
    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume: dibuka lagi ")
    }


}