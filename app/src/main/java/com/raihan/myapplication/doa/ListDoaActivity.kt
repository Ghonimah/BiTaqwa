package com.raihan.myapplication.doa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityListDoaBinding
import com.raihan.myapplication.doa.adapter.DoaListAdapter
import com.raihan.myapplication.doa.data.*
import com.raihan.myapplication.doa.model.DoaModel

class ListDoaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListDoaBinding

    lateinit var title: String
    var logo: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListDoaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = intent.getStringExtra("ext_title").toString()
        logo = intent.getIntExtra("ext_icon", 0)

        setSupportActionBar(binding.toolbarListDoa)
        supportActionBar?.title = title
        initRecyclerview()
    }

    private fun initRecyclerview() {
        val list: ArrayList<DoaModel> = arrayListOf()
        when (title) {
            getString(R.string.text_pagi_amp_malam) -> list.addAll(DoaPagiDanMalamData.listDoaPagiDanMalamData)
            getString(R.string.text_rumah) -> list.addAll(DoaRumahData.listDoaRumahData)
            getString(R.string.Makan_amp_Minum) -> list.addAll(DoaMakanDanMinumData.listDoaMakananDanMinumanData)
            getString(R.string.text_perjalanan) -> list.addAll(DoaPerjalananData.listDoaPerjalananData)
            getString(R.string.text_doa_sholat) -> list.addAll(DoaSholatData.listDoaShalatData)
            getString(R.string.text_etika_baik) -> list.addAll(DoaEtikaBaikData.listDoaEtikaBaikData)
        }

        val adapterDoa = DoaListAdapter(list, title, logo)
        binding.rvDoa.setHasFixedSize(true)
        binding.rvDoa.layoutManager = LinearLayoutManager(this)
        binding.rvDoa.adapter = adapterDoa

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return super.onOptionsItemSelected(item)
    }
}