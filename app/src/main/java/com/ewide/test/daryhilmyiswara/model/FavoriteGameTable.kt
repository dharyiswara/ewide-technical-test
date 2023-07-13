package com.ewide.test.daryhilmyiswara.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "favorite_game",
    primaryKeys = ["id"]
)
data class FavoriteGameTable(
    @SerializedName("id") val id: String,
    @SerializedName("price") val price: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)