package com.bqubique.quran_randomayah.model.verse


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("city")
    val city: Any,
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: Position,
    @SerializedName("title")
    val title: String
)