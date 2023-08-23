package com.capstone.metricapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CommonResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
