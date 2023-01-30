package com.raihan.myapplication.videokajian.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raihan.myapplication.R
import com.raihan.myapplication.videokajian.DetailVideoKajianActivity
import com.raihan.myapplication.videokajian.model.VideoKajianModel
import java.nio.file.Files.size

// 1. implement member
class AdapterKajian(private val dataList: ArrayList<VideoKajianModel>) :
    RecyclerView.Adapter<AdapterKajian.ViewHolder>() {

    // 2.
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivPhoto: ImageView = view.findViewById(R.id.iv_item_photo)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvSpeaker: TextView = view.findViewById(R.id.tv_speaker)
        var tvChannel: TextView = view.findViewById(R.id.tv_channel)
    }

    // 3.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video_kajian, parent, false)

        return ViewHolder(view)
    }

    // 4.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // mengambil data berdasarkan no antrian / urutan
        val itemData = dataList[position]

        // tempelkan ke komponen
        Glide.with(holder.itemView.context)
            .load(itemData.thumbnail)
            .into(holder.ivPhoto)
        holder.tvTitle.text = itemData.title
        holder.tvChannel.text = itemData.channel
        holder.tvSpeaker.text = itemData.speaker

        // biar bisa di klik pindah ke detail
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(holder.itemView.context, DetailVideoKajianActivity::class.java)
            intent.putExtra(DetailVideoKajianActivity.KUNCI_VIDEO_KAJIAN, itemData)
            view.context.startActivity(intent)
        }
    }

    // 5.
    override fun getItemCount(): Int {
        return dataList.size
    }
}