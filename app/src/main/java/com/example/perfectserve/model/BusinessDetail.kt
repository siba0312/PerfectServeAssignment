package com.example.perfectserve.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BusinessDetail (

    @SerializedName("businesses") val businesses : ArrayList<Businesses>,
    @SerializedName("total") val total : Int,
    @SerializedName("region") val region : Region
): Serializable