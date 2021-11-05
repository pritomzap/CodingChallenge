package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AffiliationsItem(

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("locationString")
	val locationString: LocationString? = null,

	@field:SerializedName("nameNow")
	val nameNow: NameNow? = null,

	@field:SerializedName("countryNow")
	val countryNow: CountryNow? = null,

	@field:SerializedName("cityNow")
	val cityNow: CityNow? = null
):Serializable