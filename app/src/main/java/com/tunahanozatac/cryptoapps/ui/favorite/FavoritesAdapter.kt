package com.tunahanozatac.cryptoapps.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.cryptoapps.databinding.ItemFavoriteBinding
import com.tunahanozatac.cryptoapps.domain.model.FavouritesUI

class FavoritesAdapter :
    ListAdapter<FavouritesUI, FavoritesAdapter.FavouriteCoinsViewHolder>(FavouritesComparator()) {

    var onDeleteClick: (FavouritesUI) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteCoinsViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteCoinsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteCoinsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }

    inner class FavouriteCoinsViewHolder(private var binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavouritesUI) {
            with(binding) {
                model = item
                btnDelete.setOnClickListener {
                    onDeleteClick(item)
                }
            }
        }
    }

    class FavouritesComparator : DiffUtil.ItemCallback<FavouritesUI>() {
        override fun areItemsTheSame(oldItem: FavouritesUI, newItem: FavouritesUI) =
            oldItem.coinId == newItem.coinId

        override fun areContentsTheSame(oldItem: FavouritesUI, newItem: FavouritesUI) =
            oldItem == newItem
    }
}