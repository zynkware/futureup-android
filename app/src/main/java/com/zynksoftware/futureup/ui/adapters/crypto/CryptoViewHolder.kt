package com.zynksoftware.futureup.ui.adapters.crypto

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.ViewHolderCryptoBinding
import com.zynksoftware.futureup.extensions.loadImage
import com.zynksoftware.futureup.models.CryptoModel

class CryptoViewHolder(private val binding: ViewHolderCryptoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bindData(item: CryptoModel) {
        binding.cryptoImageView.loadImage(item.image)
        binding.cryptoNameTextView.text = item.name
        binding.percentageTextView.text = "${String.format("%.2f", item.priceChangePercentage24H)}%"
        if (item.priceChangePercentage24H == null || item.priceChangePercentage24H < 0) {
            binding.imageBackgroundImageView.setBackgroundColor(
                ContextCompat.getColor(context, R.color.circular_image_red)
            )
            binding.percentageTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            binding.imageBackgroundImageView.setBackgroundColor(
                ContextCompat.getColor(context, R.color.circular_image_green)
            )
            binding.percentageTextView.setTextColor(ContextCompat.getColor(context, R.color.green))
        }
        if (item.image.isNullOrBlank()) {
            binding.imageBackgroundImageView.setBackgroundColor(
                ContextCompat.getColor(context, R.color.circular_image_gray)
            )
        }
        binding.cryptoValueTextView.text = context.getString(R.string.coin_value, "${item.currentPrice}")
    }
}