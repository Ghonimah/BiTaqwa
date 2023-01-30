package com.raihan.myapplication.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.raihan.myapplication.R
import com.raihan.myapplication.home.model.InspirationModel

class InspirationAdapter(
    private val ListInspiration: ArrayList<InspirationModel>
    ) : RecyclerView.Adapter<InspirationAdapter.ViewHolder>() {

    // menghubungkan komponen layout dengan kotlin
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // panggil imageview pada item_inspiration_xml
        var imgView: ImageView = itemView.findViewById(R.id.img_inspiration)
    }

    // membuat tampilan di recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspiration, parent, false)
        return ViewHolder(view)
    }

    // memasang tampilan di recyclerview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inspiration = ListInspiration[position]

        // tampilan gambar ke imageview
        holder.imgView.setImageResource(inspiration.inspirationImage)
    }

    // menentukan jumlah data di recyclerview
    override fun getItemCount(): Int {
        // nampilin data sebanyak foto yang ada di file InspirationData
        return ListInspiration.size
    }
}