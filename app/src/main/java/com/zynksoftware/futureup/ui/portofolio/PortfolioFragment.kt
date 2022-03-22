package com.zynksoftware.futureup.ui.portofolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.shape.CornerFamily
import com.zynksoftware.futureup.R
import com.zynksoftware.futureup.databinding.FragmentPortfolioBinding
import com.zynksoftware.futureup.ui.adapters.portfolio.PortfolioAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PortfolioFragment : Fragment() {

    private var binding: FragmentPortfolioBinding? = null
    private val viewModel: PortfolioViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.portfolioRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.portfolioLiveData.observe(this) { portfolioCoins ->
            binding?.portfolioRecyclerView?.adapter = PortfolioAdapter(portfolioCoins)
        }

        viewModel.portfolioBalance.observe(this) { balance ->
            binding?.dashboardBalanceValueTextView?.text = getString(R.string.coin_value, balance)
        }
        setUpDashboard()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPortfolioCoins()
    }

    private fun setUpDashboard() {
        binding?.dashboardCardView?.shapeAppearanceModel =
            binding?.dashboardCardView?.shapeAppearanceModel?.toBuilder()
                ?.setTopLeftCorner(CornerFamily.ROUNDED, 0f)
                ?.setTopRightCorner(CornerFamily.ROUNDED, 0f)
                ?.build()!!
        binding?.backImageView?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}