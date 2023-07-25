package com.egorpoprotskiy.myshopping.domain

class AddShopItemUseCase(private val shopRepository: ShopRepository) {
    // 10
    suspend fun addShopItem(shopItem: ShopItem) {
        shopRepository.addShopItem(shopItem)
    }
}