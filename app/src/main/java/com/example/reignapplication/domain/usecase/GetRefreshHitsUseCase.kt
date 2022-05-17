package com.example.reignapplication.domain.usecase

import com.example.reignapplication.data.HitsRepository
import com.example.reignapplication.data.database.entities.toDatabase
import com.example.reignapplication.domain.model.Hit
import javax.inject.Inject

class GetRefreshHitsUseCase @Inject constructor(
    private val repository: HitsRepository,
) {
    suspend operator fun invoke(): List<Hit> {
        val hits = repository.getAllHitsFromApi()
        hits?.let { hit ->
            repository.clearHits()
            repository.insertHits(hit.map { it.toDatabase() })
        }
        return repository.getAllHitsFromDatabase()

    }
}