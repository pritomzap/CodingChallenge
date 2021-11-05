package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResidencesItem(

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("locationString")
	val locationString: LocationString? = null,

	@field:SerializedName("countryNow")
	val countryNow: CountryNow? = null
): Serializable