package com.egorpoprotskiy.myshopping.domain

class GetShopItemUseCase(private val shopRepository: ShopRepository) {
    fun getShopItem(shopItem: Int): ShopItem {
        return shopRepository.getShopItem(shopItem)
    }
}