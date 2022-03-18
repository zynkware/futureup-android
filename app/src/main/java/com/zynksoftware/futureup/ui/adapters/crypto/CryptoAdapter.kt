package com.zynksoftware.futureup.ui.adapters.crypto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zynksoftware.futureup.databinding.ViewHolderCryptoBinding
import com.zynksoftware.futureup.models.CryptoModel

class CryptoAdapter(
    private val items: List<CryptoModel>,
    private var onItemClick: ((CryptoModel) -> Unit)? = null
): RecyclerView.Adapter<CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(ViewHolderCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}