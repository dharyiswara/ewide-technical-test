package com.ewide.test.daryhilmyiswara.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("info") val info: GameInfo?,
    @SerializedName("cheapestPriceEver") val cheapestPrice: CheapestPrice?
) {

    data class GameInfo(
        @SerializedName("title") val title: String?,
        @SerializedName("steamAppID") val steamAppId: String?,
        @SerializedName("thumb") val image: String?
    )

    data class CheapestPrice(
        @SerializedName("price") val price: String?,
        @SerializedName("date") val date: Long?
    )

}