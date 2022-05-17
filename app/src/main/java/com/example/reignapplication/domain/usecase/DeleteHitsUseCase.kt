package com.example.reignapplication.domain.usecase

import com.example.reignapplication.data.HitsRepository
import javax.inject.Inject

class DeleteHitsUseCase @Inject constructor(
    private val repository: HitsRepository,
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteHit(id = id)
    }
}