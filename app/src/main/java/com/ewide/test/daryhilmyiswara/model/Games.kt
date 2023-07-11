package com.ewide.test.daryhilmyiswara.model

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("gameID") val id: String?,
    @SerializedName("steamAppID") val steamAppId: String?,
    @SerializedName("cheapest") val price: String?,
    @SerializedName("external") val name: String?,
    @SerializedName("thumb") val image: String?
)
