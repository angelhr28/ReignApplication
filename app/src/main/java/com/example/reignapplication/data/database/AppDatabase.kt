package com.example.reignapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reignapplication.data.database.dao.HitDao
import com.example.reignapplication.data.database.entities.HitEntity

@Database(entities = [HitEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun developerDao(): HitDao
}