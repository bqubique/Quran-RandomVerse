package com.bqubique.noble_reminder.model.arabic_verse


import com.google.gson.annotations.SerializedName


data class Meta(
    @SerializedName("filters")
    val filters: Filters
)