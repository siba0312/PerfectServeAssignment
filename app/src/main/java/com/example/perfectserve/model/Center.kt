package com.example.perfectserve.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Center (

	@SerializedName("longitude") val longitude : Double,
	@SerializedName("latitude") val latitude : Double
): Serializable