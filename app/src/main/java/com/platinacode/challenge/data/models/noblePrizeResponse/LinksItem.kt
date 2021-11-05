package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinksItem(

	@field:SerializedName("types")
	val types: String? = null,

	@field:SerializedName("rel")
	val rel: String? = null,

	@field:SerializedName("action")
	val action: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: List<String?>? = null
): Serializable