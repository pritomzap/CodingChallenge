package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseNoblePrize(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("laureates")
	val laureates: List<LaureatesItem> = listOf()
): Serializable