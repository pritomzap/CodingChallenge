package com.platinacode.challenge.data.models.noblePrizeResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NobelPrizesItem(

	@field:SerializedName("awardYear")
	val awardYear: String? = null,

	@field:SerializedName("categoryFullName")
	val categoryFullName: CategoryFullName? = null,

	@field:SerializedName("prizeAmount")
	val prizeAmount: Int? = null,

	@field:SerializedName("prizeAmountAdjusted")
	val prizeAmountAdjusted: Int? = null,

	@field:SerializedName("sortOrder")
	val sortOrder: String? = null,

	@field:SerializedName("portion")
	val portion: String? = null,

	@field:SerializedName("motivation")
	val motivation: Motivation? = null,

	@field:SerializedName("affiliations")
	val affiliations: List<AffiliationsItem?>? = null,

	@field:SerializedName("dateAwarded")
	val dateAwarded: String? = null,

	@field:SerializedName("links")
	val links: List<LinksItem?>? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("prizeStatus")
	val prizeStatus: String? = null,

	@field:SerializedName("residences")
	val residences: List<ResidencesItem?>? = null
): Serializable