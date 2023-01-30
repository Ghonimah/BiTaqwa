package com.raihan.myapplication.doa.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DoaModel(
    var title: String = "",
    var doa: String = "",
    var translate: String = "",
    var latin: String = "",
    var profile: String = ""
) : Parcelable