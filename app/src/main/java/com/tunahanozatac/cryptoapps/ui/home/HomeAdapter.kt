package com.tunahanozatac.cryptoapps.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.cryptoapps.R
import com.tunahanozatac.cryptoapps.databinding.ItemCryptoListBinding
import com.tunahanozatac.cryptoapps.domain.model.CoinMarketsUI

class HomeAdapter : ListAdapter<CoinMarketsUI, HomeAdapter.CoinsViewHolder>(CoinComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding =
            ItemCryptoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class CoinsViewHolder(private var binding: ItemCryptoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CoinMarketsUI) {
            with(binding) {
                model = item
                root.animation = AnimationUtils.loadAnimation(
                    binding.root.context, R.anim.animation_rv
                )
                root.setOnClickListener {
                    item.coinId?.let { coinId ->
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToCryptoDetailsFragment(coinId)
                        it.findNavController().navigate(action)
                    }
                }
            }
        }
    }

    class CoinComparator : DiffUtil.ItemCallback<CoinMarketsUI>() {
        override fun areItemsTheSame(oldItem: CoinMarketsUI, newItem: CoinMarketsUI) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: CoinMarketsUI, newItem: CoinMarketsUI) =
            oldItem == newItem
    }
}