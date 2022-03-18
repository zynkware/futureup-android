package com.zynksoftware.futureup.usecase

import com.zynksoftware.futureup.Repository
import com.zynksoftware.futureup.network.Resource
import kotlinx.coroutines.flow.flow

class GetCoinsUseCase(private val repository: Repository) {

    suspend fun getCoins() = flow {
        emit(Resource.Loading())
        val response = repository.getCoins()
        emit(response)
    }
}