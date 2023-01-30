package com.raihan.myapplication.home.data

import com.raihan.myapplication.R
import com.raihan.myapplication.home.model.InspirationModel

object InspirationData {
    private val inspirationImage = intArrayOf(
        R.drawable.img_inspiration,
        R.drawable.inspirasi_islam1,
        R.drawable.inspirasi_islam2,
        R.drawable.inspirasi_islam3
    )
    // memasukkan data ke model
    val listData: ArrayList<InspirationModel>
    get() {
        val list = arrayListOf<InspirationModel>()
        for (position in inspirationImage.indices) {
            val inspiration = InspirationModel()
            inspiration.inspirationImage = inspirationImage[position]
            list.add(inspiration)
        }

        return list
    }

}