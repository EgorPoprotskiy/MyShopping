package com.egorpoprotskiy.myshopping.domain

data class ShopList(
    val name: String,
    val count: Int,
    val price: Int,
    val active: Boolean,
    var id: Int = ID_NOTFOUND
) {
    companion object {
        const val ID_NOTFOUND = -1
    }
}