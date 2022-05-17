package com.example.reignapplication.domain.model

import com.example.reignapplication.data.database.entities.HitEntity
import com.example.reignapplication.data.model.HitModel

data class Hit(
    val id: Int?,
    val author: String?,
    val title: String?,
    val commentText: String?,
    val url: String?,
    val createdAt: String?,
)

fun HitModel.toDomain() = Hit(
    null, author, title ?: storyTitle, commentText, url ?: storyUrl, createdAt
)

fun HitEntity.toDomain() = Hit(
    id, author, title, commentText, url, createdAt
)