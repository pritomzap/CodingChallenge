package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KnownName(

	@field:SerializedName("se")
	val se: String? = null,

	@field:SerializedName("en")
	val en: String? = null
): Serializable