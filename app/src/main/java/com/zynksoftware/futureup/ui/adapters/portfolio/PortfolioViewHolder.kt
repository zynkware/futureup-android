package com.zynksoftware.futureup.ui.adapters.portfolio

import androidx.recyclerview.widget.RecyclerView
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.ViewHolderPortfolioBinding
import com.zynksoftware.futureup.extensions.loadImage
import com.zynksoftware.futureup.models.PortfolioCoinModel

class PortfolioViewHolder(private val binding: ViewHolderPortfolioBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bindData(item: PortfolioCoinModel) {
        binding.coinNamePortfolio.text = item.name
        binding.coinSymbolPortofilo.text = item.symbol
        val tokenAmount = item.token_amount.toString() + " " + item.symbol
        binding.tokenAmountValue.text = tokenAmount
        binding.usdValue.text = context.getString(R.string.coin_value, "${item.usd_value}")
        binding.coinImagePortfolio.loadImage(item.image)
    }
}