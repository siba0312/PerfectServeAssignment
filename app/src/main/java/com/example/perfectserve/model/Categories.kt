package com.example.perfectserve.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categories (

	@SerializedName("alias") val alias : String,
	@SerializedName("title") val title : String
): Serializable