package com.raihan.myapplication.zakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityZakatBinding
import java.text.NumberFormat
import java.util.*

class ZakatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityZakatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZakatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarZakat)

        initView()

    }

    private fun initView() {
        binding.btnZakat.setOnClickListener {
            val totalHarta = binding.etAmountZakat.getNumericValue()

            val formatAmount = NumberFormat.getNumberInstance(Locale("id", "ID"))
                .format(binding.etAmountZakat.getNumericValue())
            binding.tvHartaAmount.text = "Rp $formatAmount"

            if (totalHarta.toInt()!! >= 85000000) {

                val zakat = totalHarta.toInt() * (2.5 / 100)

                val formatZakat = NumberFormat.getNumberInstance(Locale("id", "ID"))
                    .format(zakat)
                binding.tvAmountZakat.text = "Rp $formatZakat"
            } else {
                Toast.makeText(
                    this@ZakatActivity,
                    "Total Harta Masih Belum Mencapai Nisab (85gr Emas)",
                    Toast.LENGTH_LONG
                ).show()
                binding.tvAmountZakat.text = "Rp 0"
            }
        }

    }
}