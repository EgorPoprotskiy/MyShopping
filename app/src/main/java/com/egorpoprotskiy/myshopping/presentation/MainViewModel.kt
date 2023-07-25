package com.egorpoprotskiy.myshopping.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egorpoprotskiy.myshopping.data.ShopListRepositoryImpl
import com.egorpoprotskiy.myshopping.domain.DeleteShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.EditShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.GetShopListUseCase
import com.egorpoprotskiy.myshopping.domain.ShopItem
import kotlinx.coroutines.launch

// 9
class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        // 11
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun changeValueState(shopItem: ShopItem) {
        // 11
        viewModelScope.launch {
            val newItem = shopItem.copy(value = !shopItem.value)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}