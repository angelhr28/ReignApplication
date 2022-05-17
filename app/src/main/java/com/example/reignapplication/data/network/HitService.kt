package com.example.reignapplication.data.network

import com.example.reignapplication.data.model.HitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HitService @Inject constructor(
    private val api: HitApiClient,
) {
    suspend fun getHits(): List<HitModel>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllHits().body()
                response?.hits
            } catch (e: Exception) {
                listOf()
            }
        }
    }
}