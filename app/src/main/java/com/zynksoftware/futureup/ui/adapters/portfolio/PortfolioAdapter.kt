package com.zynksoftware.futureup.ui.adapters.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zynksoftware.futureup.databinding.ViewHolderPortfolioBinding
import com.zynksoftware.futureup.models.PortfolioCoinModel

class PortfolioAdapter(
    private val items: List<PortfolioCoinModel>,
    private var onItemClick: ((PortfolioCoinModel) -> Unit)? = null
) : RecyclerView.Adapter<PortfolioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        return PortfolioViewHolder(
            ViewHolderPortfolioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
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


