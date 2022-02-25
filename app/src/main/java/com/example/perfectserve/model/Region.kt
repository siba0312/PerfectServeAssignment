package com.example.perfectserve.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Region (
	@SerializedName("center") val center : Center
): Serializable