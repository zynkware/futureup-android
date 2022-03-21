package com.zynksoftware.futureup.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zynksoftware.futureup.databinding.FragmentWalletBinding
import com.zynksoftware.futureup.extensions.navigateToNextDestination
import com.zynksoftware.futureup.models.CardModel
import com.zynksoftware.futureup.ui.adapters.crypto.CryptoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WalletFragment : Fragment() {

    private var binding: FragmentWalletBinding? = null

    private val viewModel: WalletViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.coinsLiveData.observe(this) { items ->
            val adapter = CryptoAdapter(items)
            binding?.recyclerView?.adapter = adapter
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        binding?.walletCardView?.setOnClickListener {
            val direction = WalletFragmentDirections.actionWalletFragmentToPortfolioFragment()
            navigateToNextDestination(direction)
        }

        viewModel.getCoins()
        setUpTopCard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setUpTopCard() {
        val card = CardModel("0", "Alexandru's Samsung", 29.200, "1234 1234 1234 1234")
        val balanceString = "$" + String.format("%.3f", card.balance)
        binding?.balanecValueTextView?.text = balanceString
        binding?.hash4?.text = card.card_number?.split(" ")?.get(3)
        binding?.holderNameTextView?.text = card.holder_name
    }
}