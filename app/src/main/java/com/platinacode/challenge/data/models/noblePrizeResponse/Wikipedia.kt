package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Wikipedia(

	@field:SerializedName("english")
	val english: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
): Serializable