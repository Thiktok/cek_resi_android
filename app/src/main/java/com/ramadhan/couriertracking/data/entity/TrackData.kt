package com.ramadhan.couriertracking.data.entity

import com.google.gson.annotations.SerializedName

data class TrackData(
    @SerializedName("summary")
    val summary: Summary,
    @SerializedName("detail")
    val info: Information,
    @SerializedName("history")
    val track: List<Tracking>
)