package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meta(

	@field:SerializedName("license")
	val license: String? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("terms")
	val terms: String? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("disclaimer")
	val disclaimer: String? = null
): Serializable