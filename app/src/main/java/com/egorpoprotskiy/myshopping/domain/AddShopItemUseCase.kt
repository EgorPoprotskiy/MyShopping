package com.egorpoprotskiy.myshopping.domain

class AddShopItemUseCase(private val shopRepository: ShopRepository) {
    fun addShopItem(shopItem: ShopItem) {
        shopRepository.addShopItem(shopItem)
    }
}