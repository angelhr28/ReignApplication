package com.example.reignapplication.data.model

import com.google.gson.annotations.SerializedName

data class HitModel(
    @field:SerializedName("author")
    val author: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("story_title")
    val storyTitle: String?,
    @field:SerializedName("created_at")
    val createdAt: String?,
    @field:SerializedName("comment_text")
    val commentText: String?,
    @field:SerializedName("url")
    val url: String?,
    @field:SerializedName("story_url")
    val storyUrl: String?,
)