package com.example.reignapplication.domain.usecase

import com.example.reignapplication.data.HitsRepository
import com.example.reignapplication.data.database.entities.toDatabase
import com.example.reignapplication.domain.model.Hit
import javax.inject.Inject

class GetHitsUseCase @Inject constructor(
    private val repository: HitsRepository,
) {
    suspend operator fun invoke(): List<Hit> {
        var hits = repository.getAllHitsFromDatabase()
        if (hits.isEmpty()) {
            val hitsRes = repository.getAllHitsFromApi()
            hitsRes?.let { list ->
                repository.clearHits()
                repository.insertHits(list.map { it.toDatabase() })
                hits = repository.getAllHitsFromDatabase()
            }
        }
        return hits
    }
}