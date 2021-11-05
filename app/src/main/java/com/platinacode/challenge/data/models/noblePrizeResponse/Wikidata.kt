package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Wikidata(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null
): Serializable