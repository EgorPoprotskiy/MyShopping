package com.egorpoprotskiy.myshopping.data

import com.egorpoprotskiy.myshopping.domain.ShopList
import com.egorpoprotskiy.myshopping.domain.ShopRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopRepository {
    private val shopListAll = mutableListOf<ShopList>()
    private var autoIncrementId = 0
    init {
        for (i in 1 until 10) {
            val item = ShopList("name $i", i, i, true)
        }
    }
    override fun addShopItem(shopList: ShopList) {
        if (shopList.id == ShopList.ID_NOTFOUND) {
            shopList.id == autoIncrementId++
        }
        shopListAll.add(shopList)
    }

    override fun deleteShopItem(shopList: ShopList) {
        shopListAll.remove(shopList)
    }

    override fun editShopItem(shopList: ShopList) {
        val olItemId = getShopItem(shopList.id)
        shopListAll.remove(olItemId)
        addShopItem(shopList)
    }

    override fun getShopItem(shopListId: Int): ShopList {
        return shopListAll.find {
            it.id == shopListId
        } ?: throw RuntimeException("Element with ID $shopListId not found")
    }

    override fun getShopList(): List<ShopList> {
        return shopListAll.toList()
    }

}