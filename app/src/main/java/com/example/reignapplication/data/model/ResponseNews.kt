package com.example.reignapplication.data.model

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @field:SerializedName("hits")
    val hits: List<HitModel>,
)