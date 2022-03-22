package com.zynksoftware.futureup

import com.zynksoftware.futureup.models.CryptoModel
import com.zynksoftware.futureup.models.PortfolioCoinModel
import com.zynksoftware.futureup.network.ApiService
import com.zynksoftware.futureup.network.RemoteServicesHandler
import com.zynksoftware.futureup.network.Resource
import java.util.ArrayList

class Repository(
    private val apiService: ApiService,
    private val servicesHandler: RemoteServicesHandler
) {

    companion object {
        private var portfolioCoins: ArrayList<PortfolioCoinModel> = arrayListOf(
            PortfolioCoinModel(
                "bitcoin",
                "BTC",
                "Bitcoin",
                "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                10000.0,
                0.25
            )
        )
    }

    suspend fun getCoins(): Resource<List<CryptoModel>> =
        servicesHandler.makeTheCallAndHandleResponse(
            serviceCall = {
                apiService.getCoins()
            },
            mapSuccess = { Resource.Success(it.body(), it.code()) }
        )

    fun getPortfolioCoinsFromDb(): ArrayList<PortfolioCoinModel> {
        return portfolioCoins
    }

    fun addCoinsToPortfolio(portfolioCoinModel: PortfolioCoinModel) {
        portfolioCoins.add(portfolioCoinModel)
    }
}