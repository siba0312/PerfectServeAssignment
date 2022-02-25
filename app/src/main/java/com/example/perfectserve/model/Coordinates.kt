package com.example.perfectserve.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coordinates (

	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double
): Serializable