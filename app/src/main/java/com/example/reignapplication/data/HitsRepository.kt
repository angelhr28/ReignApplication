package com.example.reignapplication.data

import com.example.reignapplication.data.database.dao.HitDao
import com.example.reignapplication.data.database.entities.HitEntity
import com.example.reignapplication.data.network.HitService
import com.example.reignapplication.domain.model.Hit
import com.example.reignapplication.domain.model.toDomain
import javax.inject.Inject

class HitsRepository @Inject constructor(
    private val api: HitService,
    private val hitDao: HitDao,
) {
    suspend fun getAllHitsFromApi(): List<Hit>? {
        val response = api.getHits()
        return response?.map { it.toDomain() }
    }

    suspend fun getAllHitsFromDatabase(): List<Hit> {
        val response = hitDao.getAllHit()
        return response.map { it.toDomain() }
    }

    suspend fun insertHits(hits: List<HitEntity>) {
        hitDao.insertAll(hits)
    }

    suspend fun clearHits() {
        hitDao.clearAll()
    }

    suspend fun deleteHit(id: Int) {
        hitDao.deleteHit(id)
    }
}