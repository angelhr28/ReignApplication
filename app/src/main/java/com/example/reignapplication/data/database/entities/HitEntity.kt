package com.example.reignapplication.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reignapplication.domain.model.Hit

@Entity(tableName = "hit_table")
data class HitEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "comment_text") val commentText: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "created_at") val createdAt: String?,
)

fun Hit.toDatabase() = HitEntity(
    author = author,
    title = title,
    commentText = commentText,
    url = url,
    createdAt = createdAt,
)