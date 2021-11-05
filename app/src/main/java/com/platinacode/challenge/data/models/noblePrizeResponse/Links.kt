package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("last")
	val last: String? = null,

	@field:SerializedName("self")
	val self: String? = null,

	@field:SerializedName("first")
	val first: String? = null
): Serializable