package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("se")
	val se: String? = null,

	@field:SerializedName("en")
	val en: String? = null,

	@field:SerializedName("sameAs")
	val sameAs: String? = null
): Serializable