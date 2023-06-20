package com.egorpoprotskiy.myshopping.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egorpoprotskiy.myshopping.domain.ShopItem
import com.egorpoprotskiy.myshopping.domain.ShopRepository
import java.lang.RuntimeException

object ShopListRepositoryImpl: ShopRepository {
    private val shopItemAll = mutableListOf<ShopItem>()
    private val shopListAllLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0
    init {
        for (i in 1 until 10) {
            val item = ShopItem("name $i", i, i, true)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.ID_NOTFOUND) {
            shopItem.id == autoIncrementId++
        }
        shopItemAll.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemAll.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val olItemId = getShopItem(shopItem.id)
        shopItemAll.remove(olItemId)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopListId: Int): ShopItem {
        return shopItemAll.find {
            it.id == shopListId
        } ?: throw RuntimeException("Element with ID $shopListId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListAllLD
    }

    fun updateShopList() {
        shopListAllLD.value = shopItemAll.toList()
    }

}