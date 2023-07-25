package com.egorpoprotskiy.myshopping.domain

class GetShopItemUseCase(private val shopRepository: ShopRepository) {
    // 10
    suspend fun getShopItem(shopItem: Int): ShopItem {
        return shopRepository.getShopItem(shopItem)
    }
}