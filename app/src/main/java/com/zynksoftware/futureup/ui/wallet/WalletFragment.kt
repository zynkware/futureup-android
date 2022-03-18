package com.zynksoftware.futureup.ui.wallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.zynksoftware.futureup.databinding.FragmentWalletBinding
import com.zynksoftware.futureup.ui.adapters.crypto.CryptoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WalletFragment: Fragment() {

    private var binding: FragmentWalletBinding? = null

    private val viewModel: WalletViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.coinsLiveData.observe(this) { items ->
            val adapter = CryptoAdapter(items) { itemClicked ->

            }

            binding?.recyclerView?.adapter = adapter
        }

        viewModel.getCoins()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}