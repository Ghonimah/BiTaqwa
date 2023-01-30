package com.raihan.myapplication.videokajian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityVideoKajianBinding
import com.raihan.myapplication.videokajian.adapter.AdapterKajian
import com.raihan.myapplication.videokajian.data.VideoKajianData
import com.raihan.myapplication.videokajian.model.VideoKajianModel

class VideoKajianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoKajianBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoKajianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMenuKajian)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list: ArrayList<VideoKajianModel> = arrayListOf()
        list.addAll(VideoKajianData.listData)

        binding.rvVideoKajian.setHasFixedSize(true)
        binding.rvVideoKajian.layoutManager = LinearLayoutManager(this)

        val adapterKajian = AdapterKajian(list)
        binding.rvVideoKajian.adapter = adapterKajian
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }

}