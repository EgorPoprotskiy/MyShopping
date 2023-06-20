package com.egorpoprotskiy.myshopping.domain

class AddShopItemUseCase(private val shopRepository: ShopRepository) {
    fun addShopItem(shopList: ShopList) {
        shopRepository.addShopItem(shopList)
    }
}