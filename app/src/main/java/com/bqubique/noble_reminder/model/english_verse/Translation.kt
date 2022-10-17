package com.bqubique.noble_reminder.model.english_verse


import com.google.gson.annotations.SerializedName


data class Translation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("resource_id")
    val resourceId: Int,
    @SerializedName("text")
    val text: String
)