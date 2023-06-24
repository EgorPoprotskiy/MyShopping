package com.egorpoprotskiy.myshopping.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.egorpoprotskiy.myshopping.R
import com.egorpoprotskiy.myshopping.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter: ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopItemLongClickListener: ((ShopItem) -> Unit) ?= null
    var onShopItemClickListener: ((ShopItem) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_ACTIVE -> R.layout.shop_item_active
            VIEW_TYPE_INACTIVE -> R.layout.shop_item_inactive
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.tvName.text = "${shopItem.name}:"
        holder.tvNameCount.text = "${shopItem.nameCount.toString()} шт"
        holder.tvPriceCount.text = "${shopItem.priceCount.toString()} Р"
        holder.tvPrice.text = "Цена за шт.:"
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        return if (shopItem.value) {
            VIEW_TYPE_ACTIVE
        } else {
            VIEW_TYPE_INACTIVE
        }
    }

    companion object {
        const val VIEW_TYPE_ACTIVE = 100
        const val VIEW_TYPE_INACTIVE = 101

        const val MAX_SIZE_POOL = 30
    }
}