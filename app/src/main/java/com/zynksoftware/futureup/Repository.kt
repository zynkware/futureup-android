package com.zynksoftware.futureup

import com.zynksoftware.futureup.models.CryptoModel
import com.zynksoftware.futureup.network.ApiService
import com.zynksoftware.futureup.network.RemoteServicesHandler
import com.zynksoftware.futureup.network.Resource

class Repository(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {

    suspend fun getCoins(): Resource<List<CryptoModel>> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = {
                apiService.getCoins()
            },
            mapSuccess = { Resource.Success(it.body(), it.code()) }
        )
}