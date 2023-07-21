package com.egorpoprotskiy.myshopping.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.egorpoprotskiy.myshopping.R
import com.egorpoprotskiy.myshopping.databinding.ShopItemActiveBinding
import com.egorpoprotskiy.myshopping.databinding.ShopItemInactiveBinding
import com.egorpoprotskiy.myshopping.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ACTIVE -> R.layout.shop_item_active
            VIEW_TYPE_INACTIVE -> R.layout.shop_item_inactive
            else -> throw RuntimeException("Unknow view type: $viewType")
        }
//        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding
        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ShopItemInactiveBinding -> {
                binding.tvName.text = "${shopItem.name}:"
                binding.tvNameCount.text = "${shopItem.nameCount.toString()} шт"
                binding.tvPriceCount.text = "${shopItem.priceCount.toString()} Р"
                binding.tvPrice.text = "Цена за шт.:"
            }
            is ShopItemActiveBinding -> {
                binding.tvName.text = "${shopItem.name}:"
                binding.tvNameCount.text = "${shopItem.nameCount.toString()} шт"
                binding.tvPriceCount.text = "${shopItem.priceCount.toString()} Р"
                binding.tvPrice.text = "Цена за шт.:"
            }
        }
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