package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScadatelListItemResponse(

	@field:SerializedName("data")
	val data: ScadatelListData? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ScadatelListData(

	@field:SerializedName("item")
	val item: List<ScadatelItem?>? = null
)
