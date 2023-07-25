package com.egorpoprotskiy.myshopping.domain

import androidx.lifecycle.LiveData

interface ShopRepository {
    // 10
    suspend fun addShopItem(shopItem: ShopItem)
    // 10
    suspend fun deleteShopItem(shopItem: ShopItem)
    // 10
    suspend fun editShopItem(shopItem: ShopItem)
    // 10
    suspend fun getShopItem(shopItemId: Int): ShopItem
    // 10
    fun getShopList(): LiveData<List<ShopItem>>
}