package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Place(

	@field:SerializedName("continent")
	val continent: Continent? = null,

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("locationString")
	val locationString: LocationString? = null,

	@field:SerializedName("countryNow")
	val countryNow: CountryNow? = null,

	@field:SerializedName("cityNow")
	val cityNow: CityNow? = null
): Serializable