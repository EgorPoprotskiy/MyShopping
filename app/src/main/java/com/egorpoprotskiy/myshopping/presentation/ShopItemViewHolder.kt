package com.egorpoprotskiy.myshopping.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egorpoprotskiy.myshopping.R
import org.w3c.dom.Text

class ShopItemViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvNameCount = view.findViewById<TextView>(R.id.tv_nameCount)
    val tvPriceCount = view.findViewById<TextView>(R.id.tv_priceCount)
}