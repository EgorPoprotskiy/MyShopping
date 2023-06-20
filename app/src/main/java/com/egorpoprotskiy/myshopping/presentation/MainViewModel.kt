package com.egorpoprotskiy.myshopping.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egorpoprotskiy.myshopping.data.ShopListRepositoryImpl
import com.egorpoprotskiy.myshopping.domain.DeleteShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.EditShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.GetShopListUseCase
import com.egorpoprotskiy.myshopping.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun shangeValueState(shopItem: ShopItem) {
        val newItem = shopItem.copy(value = !shopItem.value)
        editShopItemUseCase.editShopItem(newItem)
    }
}