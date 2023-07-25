package com.egorpoprotskiy.myshopping.domain

data class ShopItem(
    val name: String,
    val nameCount: Int,
    val priceCount: Int,
    val value: Boolean,
    var id: Int = ID_NOTFOUND
) {
    companion object {
        // 9
        const val ID_NOTFOUND = 0
    }
}