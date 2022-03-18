package com.zynksoftware.futureup.ui.adapters.crypto

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.ViewHolderCryptoBinding
import com.zynksoftware.futureup.extensions.loadImage
import com.zynksoftware.futureup.models.CryptoModel

class CryptoViewHolder (private val binding: ViewHolderCryptoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bindData(item: CryptoModel) {
        binding.cryptoLogoImageView.loadImage(item.image)
        binding.cryptoNameTextView.text = item.name
        binding.percentageTextView.text = "${item.priceChangePercentage24H}%"
        if (item.priceChangePercentage24H == null || item.priceChangePercentage24H < 0) {
            binding.imageBackgroundImageView.setCardBackgroundColor(
                ContextCompat.getColor(context, R.color.red_opacity_50)
            )
            binding.percentageTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            binding.imageBackgroundImageView.setCardBackgroundColor(
                ContextCompat.getColor(context, R.color.green_opacity_50)
            )
            binding.percentageTextView.setTextColor(ContextCompat.getColor(context, R.color.green))

        }

        binding.cryptoValueTextView.text = "${item.currentPrice} USD Value"
    }
}