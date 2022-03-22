package com.zynksoftware.futureup

import com.zynksoftware.futureup.models.PortfolioCoinModel
import java.util.ArrayList

object TemporaryDB {
    var myCoins: ArrayList<PortfolioCoinModel> = arrayListOf(
        PortfolioCoinModel(
            "BTC",
            "BTC",
            "Bitcoin",
            "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
            10000.0,
            0.25
        )
    )
}