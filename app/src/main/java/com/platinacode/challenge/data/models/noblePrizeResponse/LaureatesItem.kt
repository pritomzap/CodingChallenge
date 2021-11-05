package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LaureatesItem(

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("death")
	val death: Death? = null,

	@field:SerializedName("givenName")
	val givenName: GivenName? = null,

	@field:SerializedName("fullName")
	val fullName: FullName? = null,

	@field:SerializedName("birth")
	val birth: Birth? = null,

	@field:SerializedName("nobelPrizes")
	val nobelPrizes: List<NobelPrizesItem> = listOf(),

	@field:SerializedName("familyName")
	val familyName: FamilyName? = null,

	@field:SerializedName("links")
	val links: List<LinksItem?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("wikipedia")
	val wikipedia: Wikipedia? = null,

	@field:SerializedName("knownName")
	val knownName: KnownName? = null,

	@field:SerializedName("wikidata")
	val wikidata: Wikidata? = null,

	@field:SerializedName("sameAs")
	val sameAs: List<String?>? = null
): Serializable