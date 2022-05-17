package com.example.reignapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reignapplication.domain.model.Hit
import com.example.reignapplication.domain.usecase.DeleteHitsUseCase
import com.example.reignapplication.domain.usecase.GetHitsUseCase
import com.example.reignapplication.domain.usecase.GetRefreshHitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitViewModel @Inject constructor(
    private val getHitsUseCase: GetHitsUseCase,
    private val getRefreshHitsUseCase: GetRefreshHitsUseCase,
    private val deleteHitsUseCase: DeleteHitsUseCase,
) : ViewModel() {

    val isRefresh = MutableLiveData<Boolean>()
    val isProgress = MutableLiveData<Boolean>()
    val snackbar = MutableLiveData<String>()
    val hitModel = MutableLiveData<List<Hit>>()

    fun onCreate() {
        viewModelScope.launch {
            isProgress.postValue(true)
            val result = getHitsUseCase()

            if (result.isEmpty()) snackbar.postValue("Sin resultados")

            hitModel.postValue(result)
            isProgress.postValue(false)
        }
    }

    fun refreshHit(isConnected: Boolean) {
        viewModelScope.launch {
            isRefresh.postValue(true)

            val result: List<Hit> = if (isConnected) getRefreshHitsUseCase() else getHitsUseCase()

            if (result.isEmpty()) snackbar.postValue("Sin resultados")

            hitModel.postValue(result)
            isRefresh.postValue(false)
        }
    }

    fun deleteHit(hit: Hit) {
        viewModelScope.launch {
            hit.id?.let { deleteHitsUseCase(it) }
        }
    }

}