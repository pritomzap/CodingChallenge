package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Birth(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("place")
	val place: Place? = null
): Serializable