package com.egorpoprotskiy.myshopping.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.egorpoprotskiy.myshopping.domain.ShopItem
import com.egorpoprotskiy.myshopping.domain.ShopRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl(application: Application): ShopRepository {
    // 8
//    private val shopListAllLD = MutableLiveData<List<ShopItem>>()
    private val shopItemDao = AppDatabase.getInstance(application).shopItemDao()
//    private val shopItemAll = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    // 8
    private val mapper = ShopItemMapper()

//    private var autoIncrementId = 0
//    init {
//        for (i in 0 until 10) {
//            val item = ShopItem("name $i", i, i, Random.nextBoolean())
//            addShopItem(item)
//        }
//    }
    override fun addShopItem(shopItem: ShopItem) {
//        if (shopItem.id == ShopItem.ID_NOTFOUND) {
//            shopItem.id = autoIncrementId++
//        }
//        shopItemAll.add(shopItem)
//        updateShopList()
        shopItemDao.addShopList(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
//        shopItemAll.remove(shopItem)
//        updateShopList()
        shopItemDao.deleteShopList(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
//        val olItemId = getShopItem(shopItem.id)
//        shopItemAll.remove(olItemId)
//        addShopItem(shopItem)
        shopItemDao.addShopList(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItem(shopListId: Int): ShopItem {
//        return shopItemAll.find {
//            it.id == shopListId
//        } ?: throw RuntimeException("Element with ID $shopListId not found")
        val dbModel = shopItemDao.getShopItem(shopListId)
        return mapper.mapDbModelToEntity(dbModel)
    }

//    override fun getShopList(): LiveData<List<ShopItem>> {
//        return shopListAllLD
//    }
override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
    addSource(shopItemDao.getShopList()) {
        value = mapper.mapItemDbModelToItemEntity(it)
    }
}

//    fun updateShopList() {
//        shopListAllLD.value = shopItemAll.toList()
//    }

}