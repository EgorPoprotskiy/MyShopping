package com.egorpoprotskiy.myshopping.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopRepository: ShopRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopRepository.getShopList()
    }
}