package com.zynksoftware.futureup.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.FragmentWalletBinding
import com.zynksoftware.futureup.extensions.navigateToNextDestination
import com.zynksoftware.futureup.ui.adapters.crypto.CryptoAdapter
import com.zynksoftware.futureup.utils.portfolio.PortfolioUtils
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
        binding?.balanceValueTextView?.text =
            getString(R.string.coin_value, "${PortfolioUtils.getTotalBalance()}")
        binding?.hash4?.text = getString(R.string.card_number)
        binding?.holderNameTextView?.text = getString(R.string.holder_name)
    }
}