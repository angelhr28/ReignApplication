package com.example.reignapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reignapplication.data.database.entities.HitEntity

@Dao
interface HitDao {

    @Query("SELECT * FROM hit_table ORDER BY created_at DESC")
    suspend fun getAllHit(): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(developers: List<HitEntity>)

    @Query("DELETE FROM hit_table")
    suspend fun clearAll()

    @Query("DELETE FROM hit_table WHERE id = :id")
    suspend fun deleteHit(id: Int)
}