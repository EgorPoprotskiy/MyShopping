package com.egorpoprotskiy.myshopping.domain

interface ShopRepository {
    fun addShopItem(shopList: ShopList)

    fun deleteShopItem(shopList: ShopList)

    fun editShopItem(shopList: ShopList)

    fun getShopItem(shopListId: Int): ShopList

    fun getShopList(): List<ShopList>
}